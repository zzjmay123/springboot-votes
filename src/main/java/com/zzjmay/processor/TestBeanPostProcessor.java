package com.zzjmay.processor;

import com.zzjmay.common.SpringCycleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自定义bean的前置和后置处理类
 * Created by zzjmay on 2019/3/3.
 */
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TestBeanPostProcessor.class);


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){

        if(bean instanceof SpringCycleBean){
            logger.info("执行了postProcessBeforeInitialization");
            ((SpringCycleBean) bean).setSpringName("postBefore");
        }

        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){

        if(bean instanceof SpringCycleBean){
            logger.info("执行了postProcessAfterInitialization");
            ((SpringCycleBean) bean).setSpringName("postafter");
        }
        return bean;
    }
}
