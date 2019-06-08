package com.zzjmay.dynamicproxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzjmay on 2019/4/28.
 */
public class TestDynamicProxy {

    public static void main(String[] args) throws Exception {

//        RealService service = (RealService) Proxy.newProxyInstance(RealService.class.getClassLoader(),new Class<?>[]{RealService.class},new MyInvocationHandler());
//
//        String param1 = "testParam";
//
//        Map<String,Object> map = new HashMap<>();
//
//        map.put("test1","hahah");
//        map.put("test2","service");
//
//        String param3 = "testParam3";
//
//        service.exectue2(param1);

       Method method = Class.forName("com.zzjmay.dynamicproxy.RealService").getMethod("execute", String.class, Class.forName("java.util.Map"), Class.forName("java.lang.String"));

       System.out.println(method);
    }
}
