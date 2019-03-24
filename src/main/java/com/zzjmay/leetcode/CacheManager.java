package com.zzjmay.leetcode;

import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用Hashmap实现一个缓存工具类,有以下特点：
 * 1。 不可变对象
 * 2.  单例
 * 3.  线程安全
 * 4   回收失效数据
 * 5   垃圾回收
 * 6   缓存大小
 * 7   LRU算法
 * Created by zzjmay on 2019/3/24.
 */
@Slf4j
public class CacheManager {

    public CacheManager() {
    }

    /**
     * 是否开启清除失效缓存
     */
    private volatile boolean clearExpireCacheEnable = true;

    /**
     * 缓存的失效时间
     */
    private long cacheTimeout = 30* 1000L;

    /**
     * 缓存使用记录
     */
    private static LinkedList<Object> cacheUseRecord = new LinkedList<>();

    /**
     * 可缓存的最大数量
     */
    private static Integer MAX_CACHE_SIZE = 10;

    //和安全有关的就要加锁

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    /**
     * 写锁
     */
    private static Lock writeLock = reentrantReadWriteLock.writeLock();

    /**
     * 读锁
     */
    private static Lock readLock = reentrantReadWriteLock.readLock();

    private final static Map<Object, CacheEntry> cacheEntryMap = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1, new NamedThreadFactory("ASYNC-SCHEDULER-", true));


    /**
     * 初始化方法
     */
    @PostConstruct
    private void init() {
        initClearTask();
    }


    public void init(long cacheTimeout){
        this.cacheTimeout = cacheTimeout;
        initClearTask();
    }
    private void initClearTask() {
        scheduler.scheduleAtFixedRate(new ClearCacheTask(),0,1, TimeUnit.MINUTES);
    }

    /**
     * 缓存类型
     */
    @Data
    private class CacheEntry {
        //上一次使用的时间
        long lastTouchTime;

        Object value;

        public CacheEntry(Object value) {
            this.value = value;
            this.lastTouchTime = System.currentTimeMillis();
        }
    }

    /**
     * 通过JVM的初始化来达到单例的获取，是比较好的作法
     * CacheManagerFactory.CACHE_MANAGER 这一做法会触发类的初始化，从而在多线程访问的时候，达到加锁的目的，即便指令进行了重排序
     *
     * @return
     */
    public static CacheManager getCacheManagerInstance() {
        return CacheManagerFactory.CACHE_MANAGER;
    }

    private static class CacheManagerFactory {
        private static final CacheManager CACHE_MANAGER = new CacheManager();
    }

    /**
     * 定时清理过期的数据
     */

    private class ClearCacheTask implements Runnable {

        @Override
        public void run() {
            log.info("##clear cache start....");
            while (clearExpireCacheEnable) {
                try {
                    long now = System.currentTimeMillis();

                    cacheEntryMap.keySet().stream().forEach(key -> {
                        //开始清理我们的过期缓存
                        try {
                            writeLock.lock();
                            //如果不在使用key列表中就直接返回
                            if (!cacheUseRecord.contains(key)) {
                                return;
                            }

                            CacheEntry cacheEntry = cacheEntryMap.get(key);

                            if (now - cacheEntry.lastTouchTime >= cacheTimeout) {
                                cacheEntryMap.remove(key);
                                cacheUseRecord.remove(key);
                                log.info("清理缓存key:{}", key);
                            }

                        } finally {
                            writeLock.unlock();
                        }

                    });

                } catch (Exception e) {
                    log.error("定时清理任务异常..", e);
                }
            }
        }
    }

    public Object get(Object key) {

        readLock.lock();
        CacheEntry entry = null;

        try {
            entry = cacheEntryMap.get(key);
        } finally {
            readLock.unlock();
        }

        if (entry == null) {
            return null;
        }

        //更新缓存访问时间
        touchCache(entry);
        //更新使用记录
        touchUseRecord(entry);


        return entry == null ? null : entry.value;
    }

    public static void touchCache(CacheEntry cacheEntry) {
        writeLock.lock();

        try {
            cacheEntry.setLastTouchTime(System.currentTimeMillis());
        } finally {
            writeLock.unlock();
        }
    }

    public static void touchUseRecord(Object key) {
        writeLock.lock();

        try {
            //删除使用记录
            cacheUseRecord.remove(key);
            cacheUseRecord.add(0, key);
        } finally {
            writeLock.unlock();
        }
    }

    public Object put(Object key, Object value) throws Exception {

        if (cacheEntryMap.size() > MAX_CACHE_SIZE) {
            deleteLRU();
        }

        if (cacheEntryMap.size() > MAX_CACHE_SIZE) {
            throw new Exception("缓存大小超出限制");
        }

        CacheEntry cacheEntry = new CacheEntry(value);

        writeLock.lock();
        try {
            cacheEntryMap.put(key, cacheEntry);
            cacheUseRecord.add(0, key);
        } finally {
            writeLock.unlock();
        }

        return value;
    }

    /**
     * 删除最不长使用的key
     */
    private void deleteLRU() {
        Object cacheKey = null;
        writeLock.lock();
        try {
            cacheKey = cacheUseRecord.remove(cacheUseRecord.size() - 1);
            cacheEntryMap.remove(cacheKey);

            log.info("LRU 清除的元素key:{}", cacheKey);
        } finally {
            writeLock.unlock();
        }
    }

    public static void delete(Object key) {
        if (null == key) {
            return;
        }

        writeLock.lock();
        try {
            cacheEntryMap.remove(key);
            cacheUseRecord.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public static void clear() {
        writeLock.lock();

        try {
            cacheEntryMap.clear();
            cacheUseRecord.clear();
        } finally {
            writeLock.unlock();
        }
    }

}
