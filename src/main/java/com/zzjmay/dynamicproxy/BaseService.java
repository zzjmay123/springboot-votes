package com.zzjmay.dynamicproxy;

import java.util.Map;

/**
 * Created by zzjmay on 2019/4/28.
 */
public interface BaseService {

    Map<String,Object> execute(String var1, Map<String, Object> var2, String var3);
}
