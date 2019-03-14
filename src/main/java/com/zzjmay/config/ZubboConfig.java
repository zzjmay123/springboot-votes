package com.zzjmay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zzjmay on 2019/3/14.
 */
@Configuration
@ImportResource(locations = {"classpath:zubbo/zubboConsumer.xml"})
public class ZubboConfig {
}
