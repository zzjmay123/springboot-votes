package com.zzjmay.dynamicproxy;

import java.util.Map;

/**
 * Created by zzjmay on 2019/4/28.
 */
public interface RealService extends BaseService {

    Map<String,Object> exectue2(String param);
}
