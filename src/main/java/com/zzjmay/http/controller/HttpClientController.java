package com.zzjmay.http.controller;

import com.zzjmay.http.service.HttpAPIService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zzjmay on 2019/2/28.
 */
@RestController
public class HttpClientController {

    @Resource
    private HttpAPIService httpAPIService;


    @RequestMapping("testHttpClient")
    private String test() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");

        System.out.println(str);

        String str2 = httpAPIService.doGet("http://www.jd.com");

        System.out.println("京东请求成功");


        return "hello";
    }
}
