package com.zzjmay.spi.annotation;

import java.lang.annotation.*;

/**
 * 创建类似dubbo的SPI机制
 *
 * Created by zzjmay on 2019/3/27.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ZSPI {

    /**
     * 默认的扩展点名
     * @return
     */
    String value() default "";
}
