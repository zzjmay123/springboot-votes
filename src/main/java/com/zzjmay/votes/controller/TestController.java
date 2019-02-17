package com.zzjmay.votes.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzjmay on 2019/2/17.
 */
public class TestController extends BaseController {

    public static void main(String[] args) {
        //1.配置规则
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("tutorial");
        // QPS 不得超出 1
        rule.setCount(5);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);

        //2.加载规则
        FlowRuleManager.loadRules(rules);

        //下面开始运行被限流作用域保护的代码

        while(true){
            Entry entry = null;

            try{
                entry = SphU.entry("tutorial");
                System.out.println("hello world");
            }catch (BlockException e){
                e.printStackTrace();
                System.out.println("blocked");
            }finally {
                if(entry != null){
                    entry.exit();
                }
            }

            try {
                //模拟
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
