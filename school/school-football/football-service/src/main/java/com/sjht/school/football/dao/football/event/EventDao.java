package com.sjht.school.football.dao.football.event;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sjht.school.football.entity.football.event.EventEntity;
import com.sjht.school.football.req.football.event.EditeEventReq;
import com.sjht.school.football.req.football.event.AutoCreateEventReq;
import com.sjht.school.football.req.football.event.MobileEventListReq;
import com.sjht.school.football.req.football.team.UpdatePlayersScoreReq;
import com.sjht.school.football.req.mobile.GetBatchNoListReq;
import com.sjht.school.football.resp.football.event.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ***************************************************
 * @ClassName EventDao
 * @Description 赛事数据层接口
 * @Author maojianyun
 * @Date 2019/9/10 9:56
 * @Version V1.0
 * ****************************************************
 **/
@Component
@Mapper
public interface EventDao extends BaseMapper<EventEntity> {

    /**
     * 分页查询赛事列表
     * @param page
     * @param params
     * @return
     */
    List<QueryEventPageListResp> queryClassroomEventPageList(Page<QueryEventPageListResp> page, @Param("params")Map<java.lang.String, Object> params);
    List<QueryEventPageListResp> queryCarnivalEventPageList(Page<QueryEventPageListResp> page, @Param("params")Map<java.lang.String, Object> params);
    List<QueryEventPageListResp> queryDayEventPageList(Page<QueryEventPageListResp> page, @Param("params")Map<java.lang.String, Object> params);

    /**
     * 分页查询赛事列表数量
     * @param params
     * @return
     */
    long queryClassroomEventPageCount(@Param("params") Map<java.lang.String, Object> params);
    long queryCarnivalEventPageCount(@Param("params") Map<java.lang.String, Object> params);
    long queryDayEventPageCount(@Param("params") Map<java.lang.String, Object> params);

    /**
     * 查询场地在指定的时间段内是否已经被使用
     * @param createEventReq
     * @return 大于0被使用 为0没有被使用
     */
    Integer querySiteIsUsed(@Param("ew") AutoCreateEventReq createEventReq);

    /**
     * 查询两个战队在相同的时间段内是否已经又赛事
     * @param createEventReq
     * @return
     */
    Integer queryIsHashEvent(@Param("ew") AutoCreateEventReq createEventReq);

    /**
     * 得到添加分数的赛事详情
     * @param eventId
     * @return
     */
    AddScoreResp getEventInfo(@Param("eventId")String eventId);

    /**
     * 添加比分
     * @param addScoreResp
     * @return
     */
    int updateScore(@Param("ew")AddScoreResp addScoreResp);

    /*
    * 查通过球队ID查看赛事
    * */

    List<EventInfoResp> showEvent(@Param("teamId")String teamId);

    /**
     * 更新赛事状态
     * @param eventId
     * @return
     */
    int updateStatus(@Param("eventId")String eventId);

    /**
     * 编辑赛事
     * @param editeEventReq
     * @return
     */
    int submitEditeEvent(@Param("ew")EditeEventReq editeEventReq);

    /**
     * 查询没有完成的赛事数量
     * @param params
     * @return
     */
    int evetIsEnd(@Param("params")Map<java.lang.String, Object> params);

    /**
     * 更新球队1的分数
     * @param playersScoreReq
     * @return
     */
    int updateEventTeam1score(@Param("ew") UpdatePlayersScoreReq playersScoreReq);

    /**
     * 更新球队2的分数
     * @param playersScoreReq
     * @return
     */
    int updateEventTeam2score(@Param("ew") UpdatePlayersScoreReq playersScoreReq);

    /**
     * 判断赛季是否已经被创建
     * @param params
     * @return List<String>
     */
    List<String> seasonAlreadyCreate(@Param("params") Map<String, Object> params);

    /**
     * 查询当天所有已完成赛事
     * @param
     * @return List<String>
     */
    List<EventEntity> showTodayEvent();

    /**
     * 查询课堂联赛相同类别、年级、班级、赛季是否还有没有完成的赛事数量
     * @param params
     * @return
     */
    int getClassRoomEventNotEndCount(@Param("params") Map<String, Object> params);

    /**
     * 查询天天见赛相同类别、年级、赛季是否还有没有完成的赛事数量
     * @param params
     * @return
     */
    int getDayEventNotEndCount(@Param("params") Map<String, Object> params);

    /**
     * 得到完成的赛事
     * @param params
     * @return
     */
    List<EventEntity> getfinshEventList(@Param("params") Map<String, Object> params);

    /**
     * 得到轮次
     * @param batchNoListReq
     * @return
     */
    List<Integer> getBatchNoList(@Param("params") GetBatchNoListReq batchNoListReq);

    /*
     * 移动端多条件查询赛事信息(栏目为天天见和嘉联华)
     * */

    List<MobileEventListResp> mobileShowEvent1(Page<MobileEventListResp> page, @Param("params")MobileEventListReq mobileEventListReq);

    /*
     * 移动端多条件查询赛事信息（课堂联赛）
     * */

    List<MobileEventListResp> mobileShowEvent2(Page<MobileEventListResp> page,@Param("params")MobileEventListReq mobileEventListReq);

    /*
     * 移动端根据赛事ID获取详情页面
     * */

    List<MobileEventInfoResp> mobileShowEventInfo(@Param("id")String id);
}
