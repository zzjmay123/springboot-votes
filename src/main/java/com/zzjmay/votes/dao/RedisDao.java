package com.zzjmay.votes.dao;

import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.List;
import java.util.Set;

/**
 * 基于Lettuce封装的缓存层操作
 * Created by zzjmay on 2019/1/20.
 */
public interface RedisDao {

    /**
     * 设置字符串的值
     * @param key
     * @param value
     * @param timeout
     */
    void setEx(String key,String value,long timeout);

    /**
     * 获取对应字符串的值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 添加zset集合
     * @param key
     * @param score
     * @param value
     */
    void zAdd(String key,double score,String value);

    /**
     * 返回当前集合的成员数量
     * @param key
     * @return
     */
    Long zCard(String key);

    /**
     * 获取zset一定范围的集合 升序
     * @param key
     * @param begin
     * @param end
     * @return
     */
    Set<String> zRange(String key,long begin,long end);

    /**
     * 获取某个具体member升序排名
     * @param key
     * @param member
     * @return
     */
    Long zRank(String key,String member);

    /**
     * 降序获取zset一定范围的集合
     * @param key
     * @param begin
     * @param end
     * @return
     */
    Set<String> zRevRange(String key,long begin,long end);

    Set<ZSetOperations.TypedTuple<String>> zRevRangeWithScore(String key, long begin, long end);

    /**
     * 获取某个具体member降序排名
     * @param key
     * @param member
     * @return
     */
    Long zRevRank(String key,String member);


    /**
     * 为有序集合中的某个member添加对应的分数
     * @param key
     * @param increment
     * @param value
     * @return
     */
    Double zIncrBy(String key,double increment,String value);

    /**
     * 执行Lua脚本的方法
     * @param script 脚本
     * @param keys key
     * @param args 参数
     * @return
     */
    <T> T  eval(DefaultRedisScript<T> script, List<String> keys,Object... args);


}
