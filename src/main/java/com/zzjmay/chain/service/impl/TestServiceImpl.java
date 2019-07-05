package com.zzjmay.chain.service.impl;

import com.zzjmay.chain.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public void queryOrderInfo() {
        log.info("测试订单信息");
    }
}
