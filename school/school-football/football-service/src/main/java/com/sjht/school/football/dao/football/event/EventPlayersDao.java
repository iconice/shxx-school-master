package com.sjht.school.football.dao.football.event;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sjht.school.football.entity.football.event.EventPlayersEntity;
import com.sjht.school.football.req.football.team.UpdatePlayersScoreReq;
import com.sjht.school.football.resp.football.statistical.GetScoreboarListResp;
import com.sjht.school.football.resp.football.statistical.GetSeasonLeaderboardListResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ***************************************************
 * @ClassName EventPlayersDao
 * @Description 赛事成员列表
 * @Author maojianyun
 * @Date 2019/9/29 8:19
 * @Version V1.0
 * ****************************************************
 **/
@Component
@Mapper
public interface EventPlayersDao extends BaseMapper<EventPlayersEntity> {

    /**
     * 跟新分数
     * @param playersScoreReq
     * @return
     */
    int updatePlayersScore(@Param("ew")UpdatePlayersScoreReq playersScoreReq);

    /**
     * 查询赛事球队学生的分数
     * @param eventId
     * @param teamId
     * @return
     */
    List<EventPlayersEntity> getPlayerScoreList(@Param("eventId")String eventId, @Param("teamId")String teamId);

    /**
     * 赛季排行榜列表
     * @param page
     * @param params
     * @return
     */
    List<GetSeasonLeaderboardListResp> getSeasonLeaderboardList(Page<GetSeasonLeaderboardListResp> page, @Param("params") Map<String, Object> params);

    /**
     * 赛季排行榜数量
     * @param params
     * @return
     */
    long getSeasonLeaderboardListCount(@Param("params") Map<String, Object> params);

    /**
     * 查询最新完成的轮次
     * @param params
     * @return
     */
    String getBatchNo(@Param("params") Map<String, Object> params);
}
