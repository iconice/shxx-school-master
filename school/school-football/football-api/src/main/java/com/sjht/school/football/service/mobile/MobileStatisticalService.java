package com.sjht.school.football.service.mobile;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.PageResult;
import com.sjht.school.football.req.football.statistical.GetScoreboarListReq;
import com.sjht.school.football.req.football.statistical.GetSeasonLeaderboardListReq;
import com.sjht.school.football.req.football.statistical.GetTopScorerListReq;

/**
 * ***************************************************
 *
 * @ClassName MobileStatisticalService
 * @Description 移动端统计
 * @Author maojianyun
 * @Date 2019/11/21 10:30
 * @Version V1.0
 * ****************************************************
 **/
public interface MobileStatisticalService {


    /**
     * 排行榜
     * @param topScorerListReq
     * @return
     */
    public BaseResponse getTopScorerList(GetTopScorerListReq topScorerListReq);

    /**
     * 积分榜
     * @param getScoreboarListReq
     * @return
     */
    public BaseResponse getScoreboardList(GetScoreboarListReq getScoreboarListReq);

    /**
     * 赛季排行榜
     * @param getSeasonLeaderboardListReq
     * @return
     */
    public BaseResponse getSeasonLeaderboardList(GetSeasonLeaderboardListReq getSeasonLeaderboardListReq);

    /**
     * 赛季积分榜
     * @param getSeasonLeaderboardListReq
     * @return
     */
    public BaseResponse getSeasonScoreboardList(GetSeasonLeaderboardListReq getSeasonLeaderboardListReq);
}
