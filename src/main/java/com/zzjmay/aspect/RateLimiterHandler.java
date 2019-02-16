package com.zzjmay.aspect;


import com.zzjmay.annotation.RateLimiter;
import com.zzjmay.common.BaseResult;
import com.zzjmay.votes.controller.BaseController;
import com.zzjmay.votes.dao.RedisDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 限流的切面
 * Created by zzjmay on 2019/2/16.
 */
@Aspect
@Component
public class RateLimiterHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterHandler.class);

    @Resource
    private RedisDao redisDao;

    private DefaultRedisScript<Long> script;

    /**
     * 在构造方法执行后进行初始化，且只会被执行一次
     */
    @PostConstruct
    private void init(){
        script = new DefaultRedisScript<>();
        //设置返回类型
        script.setResultType(Long.class);
        //加载脚本源
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("luafile/limitIp.lua")));
    }

    /**
     * 设置切点，表示只要被RateLimiter注解修饰的生效
     * rateLimiter()表示切点的签名
     */
    @Pointcut("@annotation(com.zzjmay.annotation.RateLimiter)")
    public void rateLimiter(){

    }

    @Around("@annotation(rateLimiter)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RateLimiter rateLimiter) throws Throwable{

        LOGGER.debug("分布式限流组件开始执行....");
        //判断当前注解是不是方法注解
        Signature signature = proceedingJoinPoint.getSignature();
        if(!(signature instanceof MethodSignature)){
            throw new IllegalArgumentException("the Annotation @RateLimter must used on method!");
        }

        //获取当前的请求的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取用户的IP地址
        String ip = BaseController.getIpAddr(request);

        //拼接模块key和ip组成唯一限流key
        String limitKey = rateLimiter.key()+":"+ip;
        //限流的次数
        long limitTimes = rateLimiter.limit();
        //限流的超时时间
        long expireTime = rateLimiter.expire();

        LOGGER.debug("RateLimiterHandler[分布式限流处理器] 参数值limitTimes={},limitTimeout={}",limitTimes,expireTime);

        //执行Lua脚本
        List<String> keyList = new ArrayList<>();

        keyList.add(limitKey);

        Long result = redisDao.eval(script,keyList,expireTime,limitTimes);

        if(result == 0){
            String msg = "由于超过单位时间=" + expireTime + "-允许的请求次数=" + limitTimes + "[触发限流]";
            LOGGER.debug(msg);
            BaseResult baseResult = new BaseResult();
            baseResult.setCode("1003");
            baseResult.setInfo("访问过于频繁，请稍后再试");
            return baseResult;
        }
        LOGGER.debug("RateLimterHandler[分布式限流处理器]限流执行结果-result={},请求[正常]响应", result);

        return proceedingJoinPoint.proceed();
    }


}
