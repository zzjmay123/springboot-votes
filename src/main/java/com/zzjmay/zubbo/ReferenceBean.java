package com.zzjmay.zubbo;

import com.zzjmay.zubbo.config.ReferenceConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * 模拟Dubbo的ReferenceBean
 * Created by zzjmay on 2019/3/14.
 */
public class ReferenceBean<T> extends ReferenceConfig<T> implements FactoryBean {


    @Override
    public Object getObject() throws Exception {
        //实际调用的是init()
        return get();
    }

    @Override
    public Class<?> getObjectType() {
        return getInterfaceClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
