package com.zzjmay.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by zzjmay on 2019/4/3.
 */
@EnableAutoConfiguration //激活自动装配
public class EnableAutoConfigBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigBootstrap.class).
                web(WebApplicationType.NONE).
                run(args);

        String helloWorld = context.getBean("helloWorld",String.class);

        System.out.println("helloworld Bean :"+helloWorld);

        context.close();
    }
}
