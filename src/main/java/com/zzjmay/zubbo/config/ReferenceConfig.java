package com.zzjmay.zubbo.config;


import com.zzjmay.zubbo.server.ProxyFactory;

/**
 * Created by zzjmay on 2019/3/14.
 */
public class ReferenceConfig<T> {

    private Class<?> interfaceClass;

    private transient volatile T ref;

    public synchronized T get(){
        if(ref == null){
            init();
        }

        return ref;
    }

    public void init(){
        ref = new ProxyFactory(interfaceClass).getProxyObject();
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
}
