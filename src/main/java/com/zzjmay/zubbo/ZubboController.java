package com.zzjmay.zubbo;

import com.zzjmay.zubbo.server.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zzjmay on 2019/3/14.
 */
@RestController
public class ZubboController {

    static {
        System.out.println("初始化ZubboController");
    }

    @Resource
    private MenuService menuServiceZsf;


    @RequestMapping("/zubbo/test")
    public String testZubbo(){
        menuServiceZsf.sayHello();
        return "OK";
    }
}
