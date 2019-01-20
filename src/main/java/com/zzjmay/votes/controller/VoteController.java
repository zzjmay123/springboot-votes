package com.zzjmay.votes.controller;

import com.zzjmay.common.BaseResult;
import com.zzjmay.common.RankResult;
import com.zzjmay.common.RankSumResult;
import com.zzjmay.votes.service.VoteService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zzjmay on 2019/1/20.
 */
@RestController
@RequestMapping("/votes")
public class VoteController {


    @Resource
    private VoteService voteService;

    @RequestMapping("/queryTop10")
    public RankResult queryTop10(){
        return voteService.queryTopPlayerList(10,"");
    }

    @RequestMapping("/insetPlayer/{playerName}")
    public BaseResult insetPlayer(@PathVariable  String  playerName){
        return voteService.createPlayer(playerName);
    }


    @RequestMapping("/queryAllSum")
    public RankSumResult queryAllSum(){
        return voteService.querySumInfo();
    }
}
