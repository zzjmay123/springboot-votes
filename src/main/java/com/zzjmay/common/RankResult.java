package com.zzjmay.common;

import com.zzjmay.votes.vo.VoteInfo;

import java.util.List;
import java.util.Set;

/**
 * Created by zzjmay on 2019/1/20.
 */
public class RankResult extends BaseResult {

    /**
     * 排名信息
     */
    private List<VoteInfo> rankList;

    public RankResult() {
    }

    public List<VoteInfo> getRankList() {
        return rankList;
    }

    public void setRankList(List<VoteInfo> rankList) {
        this.rankList = rankList;
    }
}
