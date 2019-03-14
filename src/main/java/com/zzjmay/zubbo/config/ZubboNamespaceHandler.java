package com.zzjmay.zubbo.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义命名空间解析
 * Created by zzjmay on 2019/3/14.
 */
public class ZubboNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {

        //注册reference标签
        registerBeanDefinitionParser("reference",new ZubboRpcBeanDefinitionParser());
    }
}
