package com.zzjmay.votes.dao.impl;

import com.zzjmay.votes.dao.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzjmay on 2019/1/20.
 */
@Service
public class RedisDaoImpl implements RedisDao {

    private static final Logger logger = LoggerFactory.getLogger(RedisDaoImpl.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void setEx(String key, String value, long timeout) {

        try {
            //按秒计算
            redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);

        }catch (Exception e){
            logger.error("添加缓存失败 key:{}",key,e);
        }
    }

    @Override
    public String get(String key) {
        String value ="";
        try {

            value = (String) redisTemplate.opsForValue().get(key);

        }catch (Exception e){
            logger.error("查询缓存异常 key:{}",key,e);
        }

        return value;
    }

    @Override
    public void zAdd(String key, double score, String value) {

        try{
            redisTemplate.opsForZSet().add(key,value,score);
        }catch (Exception e){
            logger.error("添加有序集合异常 key:{},value:{}",key,value,e);
        }
    }

    @Override
    public Set<String> zRange(String key, long begin, long end) {
        Set<String> set = null;

        try{
            set = redisTemplate.opsForZSet().range(key,begin,end);
        }catch (Exception e){
            logger.error("获取有序集合异常 key:{}",key,e);
        }

        return set;
    }

    @Override
    public Long zRank(String key, String member) {
        Long rank = null;

        try{
            rank = redisTemplate.opsForZSet().rank(key,member);
        }catch (Exception e){
            logger.error("获取成员排名异常 member:{}",member,e);
        }

        return rank;
    }


    @Override
    public Set<String> zRevRange(String key, long begin, long end) {
        Set<String> set = null;

        try{
            set = redisTemplate.opsForZSet().reverseRange(key,begin,end);
        }catch (Exception e){
            logger.error("获取有序集合异常 key:{}",key,e);
        }

        return set;
    }

    @Override
    public Long zRevRank(String key, String member) {
        Long rank = null;

        try{
            rank = redisTemplate.opsForZSet().reverseRank(key,member);
        }catch (Exception e){
            logger.error("获取成员排名异常 member:{}",member,e);
        }

        return rank;
    }

    @Override
    public Double zIncrBy(String key, double increment, String value) {
        Double d = null;

        try{
            d = redisTemplate.opsForZSet().incrementScore(key,value,increment);
        }catch (Exception e){
            logger.error("给成员增加分数异常 key:{},value:{}",key,value,e);
        }

        return d;
    }
}
