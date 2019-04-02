package com.zzjmay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 这是一个配置类
 * Created by zzjmay on 2019/4/2.
 */
@Configuration
public class HelloWorldConfiguration implements Serializable {


    @Bean
    public String helloWorld(){

        return "Hello world 2018";
    }
}
