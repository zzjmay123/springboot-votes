package com.zzjmay.bootstrap;

import com.zzjmay.annotation.EnableHelloWord;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 通过这个引导类，
 * Created by zzjmay on 2019/4/2.
 */
@EnableHelloWord
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootstrap.class).web(WebApplicationType.NONE).run(args);

        String hello = context.getBean("helloWorld",String.class);

        System.out.println(hello);
    }
}
