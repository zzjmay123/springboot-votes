package com.zzjmay.votes.vo;

import java.io.Serializable;

/**
 * Created by zzjmay on 2019/1/20.
 */
public class VoteInfo implements Serializable {

    /**
     * 选手名称
     */
    private String playerName;

    /**
     * 选手当前的票数
     */
    private Double voteNum;

    /**
     * 排名
     */
    private Long rank;

    /**
     * 与前一名的差距
     */
    private Long gapNum;

    public VoteInfo() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getGapNum() {
        return gapNum;
    }

    public void setGapNum(Long gapNum) {
        this.gapNum = gapNum;
    }

    public Double getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Double voteNum) {
        this.voteNum = voteNum;
    }
}
