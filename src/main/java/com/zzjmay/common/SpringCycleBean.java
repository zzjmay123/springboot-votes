package com.zzjmay.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 测试Spring生命周期的类
 * Created by zzjmay on 2019/3/3.
 */
@Component
public class SpringCycleBean implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(SpringCycleBean.class);

    private String springName;


    public SpringCycleBean() {
        super();
        logger.info("SpringCycleBean 执行了构造方法");
        this.springName = "构造方法";
    }

    /**
     * 初始化阶段调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("##SpringCycleBean 执行了初始化afterPropertiesSet ");
        springName = "afterPropertiesSet";
    }

    public void init(){
        logger.info("SpringCycleBean 自定义的初始化方法 init");
    }

    @PostConstruct
    public void usePostConstructInit(){
        logger.info("SpringCycleBean 自定义的构造方法");
    }


    public String getSpringName() {
        return springName;
    }

    public void setSpringName(String springName) {
        this.springName = springName;
    }
}
