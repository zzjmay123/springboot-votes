package com.zzjmay.votes.service;

import com.zzjmay.common.BaseResult;
import com.zzjmay.common.RankResult;
import com.zzjmay.common.RankSumResult;

/**
 * 投票service
 * Created by zzjmay on 2019/1/20.
 */
public interface VoteService {

    /**
     * 创建选手，初始化投票为0
     * @param playerName
     * @return
     */
    BaseResult createPlayer(String playerName);

    /**
     * 为喜欢的选手投票
     * @param playerName
     * @return
     */
    BaseResult votePlayer(String playerName,String ip);

    /**
     * 查询top前几的列表
     * @param topNum
     * @return
     */
    RankResult queryTopPlayerList(int topNum,String playerName);

    /**
     * 查询总的信息
     * @return
     */
    RankSumResult querySumInfo();

}
