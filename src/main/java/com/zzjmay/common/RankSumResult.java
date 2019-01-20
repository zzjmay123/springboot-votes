package com.zzjmay.common;

import java.io.Serializable;

/**
 * Created by zzjmay on 2019/1/20.
 */
public class RankSumResult extends BaseResult implements Serializable {

    private Long sumPlayerNum;


    private Double sumVotesNum;

    public RankSumResult() {
    }

    public Long getSumPlayerNum() {
        return sumPlayerNum;
    }

    public void setSumPlayerNum(Long sumPlayerNum) {
        this.sumPlayerNum = sumPlayerNum;
    }

    public Double getSumVotesNum() {
        return sumVotesNum;
    }

    public void setSumVotesNum(Double sumVotesNum) {
        this.sumVotesNum = sumVotesNum;
    }
}
