package com.zzjmay.configuration.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class BeforeApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //容器加载完成后触发的事件
        System.out.println("BeforeApplicationListener start:"+contextRefreshedEvent.getApplicationContext().getId()+"time:"+contextRefreshedEvent.getTimestamp());
    }
}
