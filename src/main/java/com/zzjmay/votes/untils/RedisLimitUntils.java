package com.zzjmay.votes.untils;

import com.zzjmay.votes.dao.RedisDao;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个小工具，根据IP进行防刷机制
 * 当前实现不具有通用性，存在代码侵入，需要通过切面实现一套侵入性不强的限流工具
 * Created by zzjmay on 2019/2/16.
 */
@Service("redisLimitUntils")
public class RedisLimitUntils {

    @Resource
    private RedisDao redisDao;

    private DefaultRedisScript<Long> script;

    private static final String IPACCESS_KEY_PREIX = "ipaccess_";

    /**
     * 在构造方法执行后进行初始化，且只会被执行一次
     */
    @PostConstruct
    private void init(){
        script = new DefaultRedisScript<>();
        //设置返回类型
        script.setResultType(Long.class);
        //加载脚本源
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("luafile/limitIp.lua")));
    }


    public boolean exec(String ip){
        List<String> keys = new ArrayList<>();

        keys.add(IPACCESS_KEY_PREIX+ip);
        //设置限流的次数，1分钟s内同一个IP不能超过5次
        return redisDao.eval(script,keys,60,5) == 1?true:false;
    }
}
