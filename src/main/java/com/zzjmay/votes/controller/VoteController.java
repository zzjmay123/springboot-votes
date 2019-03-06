package com.zzjmay.votes.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.zzjmay.common.BaseResult;
import com.zzjmay.common.RankResult;
import com.zzjmay.common.RankSumResult;
import com.zzjmay.common.SpringCycleBean;
import com.zzjmay.votes.service.VoteService;
import com.zzjmay.votes.service.impl.VoteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zzjmay on 2019/1/20.
 */
@RestController
@RequestMapping("/votes")
public class VoteController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Resource
    private VoteService voteService;

    @Resource
    private SpringCycleBean springCycleBean;

    @RequestMapping("/queryTop/{rank}")
    public RankResult queryTop10(@PathVariable int rank){
        return voteService.queryTopPlayerList(rank,"");
    }

    @RequestMapping("/insetPlayer/{playerName}")
    public BaseResult insetPlayer(@PathVariable  String  playerName){
        return voteService.createPlayer(playerName);
    }

    @RequestMapping("/vote/{playerName}")
    public BaseResult votePlayer(@PathVariable String playerName){
        return voteService.votePlayer(playerName);
    }

    @RequestMapping("/queryAllSum")
    public RankSumResult queryAllSum(){
        logger.info("VoteController 最后的名字",springCycleBean.getSpringName());
        return voteService.querySumInfo();
    }


}
