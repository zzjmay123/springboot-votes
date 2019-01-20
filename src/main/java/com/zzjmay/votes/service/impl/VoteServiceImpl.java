package com.zzjmay.votes.service.impl;

import com.zzjmay.common.BaseResult;
import com.zzjmay.common.Constants;
import com.zzjmay.common.RankResult;
import com.zzjmay.common.RankSumResult;
import com.zzjmay.votes.dao.RedisDao;
import com.zzjmay.votes.service.VoteService;
import com.zzjmay.votes.vo.VoteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zzjmay on 2019/1/20.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);

    @Resource
    private RedisDao redisDao;

    @Override
    public BaseResult createPlayer(String playerName) {
        BaseResult baseResult = new BaseResult();
        //判断当前的选手是否存在

        try{
            if(isNotExistPlayer(playerName)){
                //说明当前的集合中没有该选手
                redisDao.zAdd(Constants.SING_VOTE_KEY,0.0,playerName);
                baseResult.setSuccess(true);
                baseResult.setInfo("成功");
                baseResult.setCode("0000");
            }

        }catch (Exception e){
            logger.error("创建选手失败 playerName:{}",playerName,e);
        }
        return baseResult;
    }


    @Override
    public BaseResult votePlayer(String playerName) {
        BaseResult baseResult = new BaseResult();

        if(isNotExistPlayer(playerName)){
            baseResult.setCode("1001");
            baseResult.setInfo("当前选手不存在，请选择其他选手");
            return baseResult;
        }

        Double voteNum = redisDao.zIncrBy(Constants.SING_VOTE_KEY,Constants.VOTE_SCORE_PER,playerName);

        if(voteNum != null && voteNum >0){
            //投票成功
            baseResult.setSuccess(true);
            baseResult.setInfo("投票成功");
            baseResult.setCode("0000");
        }

        return baseResult;
    }

    @Override
    public RankResult queryTopPlayerList(int topNum, String playerName) {
        RankResult rankResult = new RankResult();

        if(StringUtils.isEmpty(playerName)){
            //查询前top用户
            Set<ZSetOperations.TypedTuple<String>> topSets = redisDao.zRevRangeWithScore(Constants.SING_VOTE_KEY,0,topNum-1);
            List<VoteInfo> voteInfoList = new ArrayList<>();

            topSets.stream().forEach((ZSetOperations.TypedTuple<String> info) -> {
                VoteInfo voteInfo = new VoteInfo();
                voteInfo.setPlayerName(info.getValue());
                voteInfo.setVoteNum(info.getScore());
                long rank = redisDao.zRevRank(Constants.SING_VOTE_KEY,info.getValue());
                voteInfo.setRank(rank + 1);
                voteInfoList.add(voteInfo);
            });

            rankResult.setRankList(voteInfoList);
            rankResult.setSuccess(true);
            rankResult.setInfo("成功");
            rankResult.setCode("0000");


        }else{
            //查询当前用户信息
        }

        return rankResult;
    }

    @Override
    public RankSumResult querySumInfo() {
        RankSumResult rankSumResult = new RankSumResult();
        try {

            //查询所有的数据进行汇总
            Long sumPlayer = 0L;

            Double sumVotesNum = 0.0;

            sumPlayer = redisDao.zCard(Constants.SING_VOTE_KEY);
            if(sumPlayer > 0){
                rankSumResult.setSumPlayerNum(sumPlayer);
                //说明有成员，计算对应的总分数
                Set<ZSetOperations.TypedTuple<String>> members = redisDao.zRevRangeWithScore(Constants.SING_VOTE_KEY,0,-1);

                //通过使用流计算处理
                sumVotesNum = members.stream().mapToDouble(ZSetOperations.TypedTuple<String>::getScore).sum();

                rankSumResult.setSumVotesNum(sumVotesNum);
                rankSumResult.setSuccess(true);
                rankSumResult.setInfo("成功");
                rankSumResult.setCode("0000");
            }



        }catch (Exception e){
            rankSumResult.setSuccess(false);
            rankSumResult.setInfo("异常");
            rankSumResult.setCode("1002");
            logger.error("获取总票数异常",e);
        }

        return rankSumResult;
    }


    private boolean isNotExistPlayer(String playerName) {
        return redisDao.zRank(Constants.SING_VOTE_KEY,playerName) == null ||
                redisDao.zRank(Constants.SING_VOTE_KEY,playerName) <0;
    }
}
