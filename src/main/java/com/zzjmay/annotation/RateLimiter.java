package com.zzjmay.annotation;

import java.lang.annotation.*;

/**
 * 限流注解
 * Target 表明只能使用在方法层面上
 *Retention 表示当前注解会存在JVM中
 * Created by zzjmay on 2019/2/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    /**
     * 限流KEY，限流KEY的目的是用于区分不同模块下的限流
     * @return
     */
    String key() default "rate:limiter";

    /**
     * 单位时间限制通过的请求数
     * @return
     */
    long limit() default 10;

    /**
     * 过期时间，单位：秒
     * @return
     */
    long expire() default 1;
}
