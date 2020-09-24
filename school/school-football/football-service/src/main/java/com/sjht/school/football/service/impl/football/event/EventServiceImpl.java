package com.sjht.school.football.service.impl.football.event;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sjht.school.common.Link.FootbalList;
import com.sjht.school.common.Link.algorithm.*;
import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.common.entity.PageResult;
import com.sjht.school.common.entity.ResultUtil;
import com.sjht.school.common.utils.DateUtils;
import com.sjht.school.common.utils.IdUtil;
import com.sjht.school.common.utils.PageUtil;
import com.sjht.school.football.dao.football.event.EventDao;
import com.sjht.school.football.dao.football.event.EventPlayersDao;
import com.sjht.school.football.dao.football.team.TeamDao;
import com.sjht.school.football.entity.football.event.EventEntity;
import com.sjht.school.football.entity.football.event.EventPlayersEntity;
import com.sjht.school.football.entity.football.team.TeamEntity;
import com.sjht.school.football.req.football.event.AutoCreateEventReq;
import com.sjht.school.football.req.football.event.EditeEventReq;
import com.sjht.school.football.req.football.event.ManualCreateEventReq;
import com.sjht.school.football.req.football.event.QueryEventPageListReq;
import com.sjht.school.football.req.football.team.PlayerScoreReq;
import com.sjht.school.football.resp.football.event.AddScoreResp;
import com.sjht.school.football.resp.football.event.QueryEventPageListResp;
import com.sjht.school.football.resp.football.team.GetPlayersListResp;
import com.sjht.school.football.resp.football.team.TeamListResp;
import com.sjht.school.football.service.football.event.EventPlayersService;
import com.sjht.school.football.service.football.event.EventService;
import com.sjht.school.football.service.football.team.TeamPlayersService;
import com.sjht.school.football.service.football.team.TeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ***************************************************
 *
 * @ClassName EventServiceImpl
 * @Description 赛事接口实现类
 * @Author maojianyun
 * @Date 2019/9/10 9:57
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class EventServiceImpl extends ServiceImpl<EventDao, EventEntity> implements IService<EventEntity>, EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private EventPlayersService eventPlayersService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamPlayersService playersService;

    @Autowired
    private EventPlayersDao eventPlayersDao;


    @Override
    public PageResult queryEventPageList(QueryEventPageListReq eventPageListReq) {
        PageResult pageResult = new PageResult();
        Map<String, Object> params = new HashMap<>();
        List<QueryEventPageListResp> datas = new ArrayList<>();
        Integer pageNo = Integer.valueOf(PageUtil.getPageNo(eventPageListReq.getLimit(), eventPageListReq.getOffset()));
        Integer pageSize = Integer.valueOf(eventPageListReq.getLimit());
        Page<QueryEventPageListResp> page = new Page<>(pageNo, pageSize);
        if (StringUtils.isNotBlank(eventPageListReq.getUserId())) {
            params.put("userId", eventPageListReq.getUserId());
        }
        if (StringUtils.isNotBlank(eventPageListReq.getEventName())) {
            params.put("eventName", eventPageListReq.getEventName());
        }
        if (StringUtils.isNotBlank(eventPageListReq.getTeamName())) {
            params.put("teamName", eventPageListReq.getTeamName());
        }
        if (null != eventPageListReq.getStartTime()) {
            params.put("startTime", eventPageListReq.getStartTime());
        }
        if (null != eventPageListReq.getEndTime()) {
            params.put("endTime", eventPageListReq.getEndTime());
        }
        if (0 != eventPageListReq.getBatchNo()) {
            params.put("batchNO", eventPageListReq.getBatchNo());
        }
        if (StringUtils.isNotBlank(eventPageListReq.getSeasonId())) {
            params.put("seasonId", eventPageListReq.getSeasonId());
        }
        if (StringUtils.isNotBlank(eventPageListReq.getClassId())) {
            params.put("classId", eventPageListReq.getClassId());
        }
        if (StringUtils.isNotBlank(eventPageListReq.getGradeId())) {
            params.put("gradeId", eventPageListReq.getGradeId());
        }
        params.put("status", eventPageListReq.getStatus());
        params.put("type", eventPageListReq.getType());
        params.put("category", eventPageListReq.getCategory());
        long count = 0;
        if (1 == eventPageListReq.getCategory()) {
            count = eventDao.queryDayEventPageCount(params);
            if (count > 0) {
                datas.addAll(eventDao.queryDayEventPageList(page, params));
            }
        } else if (2 == eventPageListReq.getCategory()) {
            count = eventDao.queryCarnivalEventPageCount(params);
            if (count > 0) {
                datas.addAll(eventDao.queryCarnivalEventPageList(page, params));
            }
        } else if (3 == eventPageListReq.getCategory()) {
            count = eventDao.queryClassroomEventPageCount(params);
            if (count > 0) {
                datas.addAll(eventDao.queryClassroomEventPageList(page, params));
            }
        }
        return pageResult.resultPage(datas, count);
    }

    @Transactional
    @Override
    public BaseResponse deleteEventByEvenId(String eventId) {
        BaseResponse baseResponse = new ObjectResponse<>();
        // 删除赛事
        this.deleteById(eventId);
        // 删除赛事得成员
        boolean b = eventPlayersService.deleteByEventId(eventId);
        // 删除赛事参加人
        return ((ObjectResponse) baseResponse).OK("删除成功");
    }

    @Override
    public BaseResponse manualCreateEvent(AutoCreateEventReq createEventReq) {
        EventEntity entity = new EventEntity();
        // 0.判断是否选了同一个队伍
        if (createEventReq.getTeam1Id().equals(createEventReq.getTeam2Id())) {
            return ResultUtil.error("选择了两个相同的球队了");
        }
        // 4.插入赛事
        entity.setId(IdUtil.getId());
        entity.setEventName(createEventReq.getEventName());
        entity.setType(createEventReq.getType());
        entity.setGradeId(createEventReq.getGradeId());
        entity.setClassId(createEventReq.getClassId());
        entity.setSiteId(createEventReq.getSiteId());
        entity.setTeam1Id(createEventReq.getTeam1Id());
        entity.setTeam2Id(createEventReq.getTeam2Id());
        entity.setStartTime(createEventReq.getStartTime());
        entity.setEndTime(createEventReq.getEndTime());
        this.insert(entity);
        return ResultUtil.OK("创建成功");
    }

    @Override
    public EventEntity getEventInfo(String eventId) {
        return this.selectById(eventId);
    }

    @Override
    public BaseResponse updateScore(AddScoreResp addScoreResp) {
        eventDao.updateScore(addScoreResp);
        return ResultUtil.OK("操作成功");
    }

    @Transactional
    @Override
    public BaseResponse createEvent(ManualCreateEventReq createEventReq) {
        List<EventEntity> datas = new ArrayList<>();
        // 插入赛事
        EventEntity entity = new EventEntity();
        String id = IdUtil.getId();
        entity.setId(id);
        entity.setSeasonId(createEventReq.getSeasonId());
        entity.setCategory(createEventReq.getCategory());
        entity.setBatchNo(createEventReq.getBatchNo());
        entity.setTeam2Id(createEventReq.getTeam2Id());
        entity.setTeam1Id(createEventReq.getTeam1Id());
        entity.setType(createEventReq.getType());
        entity.setSiteId(createEventReq.getSiteId());
        entity.setGradeId(createEventReq.getGradeId());
        entity.setClassId(createEventReq.getClassId());
        // 插入赛事的成员
        addEventPlayers(entity, entity.getCategory());

        this.insert(entity);
        return ResultUtil.OK("操作成功");
    }

    @Transactional
    @Override
    public BaseResponse autoCreateEvent(AutoCreateEventReq createEventReq) throws Exception {
        List<BuildEvent> events = new ArrayList<>();
        // 查询出队伍
        Map<String, Object> params = new HashMap<>();
        List<TeamListResp> dataList = new ArrayList<>();
        params.put("category", createEventReq.getCategory());
        params.put("seasonId", createEventReq.getSeasonId());   
        if (1 == createEventReq.getCategory()) {
            params.put("gradeId", createEventReq.getGradeId());
            dataList.addAll(teamDao.getDayeamList(params));
        }
        if (2 == createEventReq.getCategory()) {
            params.put("gradeId", createEventReq.getGradeId());
            dataList.addAll(teamDao.getCarnivalTeamList(params));
        }
        if (3 == createEventReq.getCategory()) {
            params.put("classId", createEventReq.getClassId());
            dataList.addAll(teamDao.getClassroomTeamList(params));
        }
        int count = eventDao.evetIsEnd(params);
        if (count == 0) {
            if (this.seasonAlreadyCreate(params)) {
                return ResultUtil.error("该赛季已经比赛过了");
            }
            List<EventEntity> entitys = new ArrayList<>();
            if (dataList != null && dataList.size() > 1) {
                events.addAll(this.eventList(dataList, createEventReq.getSiteIds()));
                for (BuildEvent buildEvent : events) {
                    EventEntity entity = new EventEntity();
                    entity.setId(IdUtil.getId());
                    entity.setGradeId(createEventReq.getGradeId());
                    if (createEventReq.getCategory() == 3) {
                        entity.setClassId(createEventReq.getClassId());
                    }
                    entity.setSiteId(buildEvent.getSiteId());
                    entity.setType(createEventReq.getType());
                    entity.setTeam1Id(buildEvent.getTeam1Id());
                    entity.setTeam2Id(buildEvent.getTeam2Id());
                    entity.setBatchNo(buildEvent.getBatchNo());
                    entity.setCategory(createEventReq.getCategory());
                    entity.setSeasonId(createEventReq.getSeasonId());
                    entitys.add(entity);
                }
                if (entitys.size() > 0) {
                    addEventPlayers(entitys, createEventReq);
                    // 添加赛事
                    this.insertBatch(entitys);
                }
            } else {
                return ResultUtil.error("该班级或该届的没有球队或球队只有1只");
            }
        } else {
            return ResultUtil.error("该班级或该届还有赛事没有完成");
        }
        return ResultUtil.OK();
    }

    /**
     * 判断该赛季是否已经创建赛事
     *
     * @param params
     * @return
     */
    public boolean seasonAlreadyCreate(Map<String, Object> params) {
        DateUtils dateUtils = DateUtils.getInstance();
        params.put("year", dateUtils.getCurrentYearSemester(dateUtils.getCurrentYear(), dateUtils.getCurrentMonth(), dateUtils.YEAR));
        params.put("term", dateUtils.getCurrentYearSemester(dateUtils.getCurrentYear(), dateUtils.getCurrentMonth(), dateUtils.SEMESTER));
        List<String> ids = eventDao.seasonAlreadyCreate(params);
        return ids.size() > 0 ? true : false;
    }

    public void addEventPlayers(List<EventEntity> entitys, AutoCreateEventReq createEventReq) {
        // 给赛事添加成员
        // 此处患处啊查询有待改进 目前先这样写
        List<EventService> eventServices = new ArrayList<>();
        List<EventPlayersEntity> entities = new ArrayList<>();
        for (EventEntity entity : entitys) {
            // 查询team1的队员
            List<GetPlayersListResp> playersList1 = playersService.getPlayersList(entity.getTeam1Id(), createEventReq.getCategory());
            for (GetPlayersListResp resp : playersList1) {
                EventPlayersEntity playersEntity = new EventPlayersEntity();
                playersEntity.setId(IdUtil.getId());
                playersEntity.setEventId(entity.getId());
                playersEntity.setTeamId(entity.getTeam1Id());
                playersEntity.setStudentId(resp.getStudentId());
                entities.add(playersEntity);
            }
            // 查询team2的队员
            List<GetPlayersListResp> playersList2 = playersService.getPlayersList(entity.getTeam2Id(), createEventReq.getCategory());
            for (GetPlayersListResp resp : playersList2) {
                EventPlayersEntity playersEntity = new EventPlayersEntity();
                playersEntity.setId(IdUtil.getId());
                playersEntity.setEventId(entity.getId());
                playersEntity.setTeamId(entity.getTeam2Id());
                playersEntity.setStudentId(resp.getStudentId());
                entities.add(playersEntity);
            }

        }
        // 构造赛事成员
        eventPlayersService.batchInser(entities);
    }

    public void addEventPlayers(EventEntity entity, Integer category) {
        // 给赛事添加成员
        // 此处患处啊查询有待改进 目前先这样写
        List<EventService> eventServices = new ArrayList<>();
        List<EventPlayersEntity> entities = new ArrayList<>();

        // 查询team1的队员
        List<GetPlayersListResp> playersList1 = playersService.getPlayersList(entity.getTeam1Id(), category);
        for (GetPlayersListResp resp : playersList1) {
            EventPlayersEntity playersEntity = new EventPlayersEntity();
            playersEntity.setId(IdUtil.getId());
            playersEntity.setEventId(entity.getId());
            playersEntity.setTeamId(entity.getTeam1Id());
            playersEntity.setStudentId(resp.getStudentId());
            entities.add(playersEntity);
        }
        // 查询team2的队员
        List<GetPlayersListResp> playersList2 = playersService.getPlayersList(entity.getTeam2Id(), category);
        for (GetPlayersListResp resp : playersList2) {
            EventPlayersEntity playersEntity = new EventPlayersEntity();
            playersEntity.setId(IdUtil.getId());
            playersEntity.setEventId(entity.getId());
            playersEntity.setTeamId(entity.getTeam2Id());
            playersEntity.setStudentId(resp.getStudentId());
            entities.add(playersEntity);
        }
        // 构造赛事成员
        eventPlayersService.batchInser(entities);
    }

    public List<BuildEvent> eventList(List<TeamListResp> dataList, List<String> siteIds) throws Exception {
        List<BuildEvent> eventNodes = new ArrayList<>();
        Random random = new Random();
        FootbalList teamLinkList1 = new FootballLinkList();
        FootbalList teamLinkList2 = new FootballLinkList();
        FootbalList siteLinkList1 = new FootballLinkList();
        int teamSize = dataList.size();
        int length = teamSize;
        if ((teamSize & 1) != 0) {// 如果队伍个数为奇数 则节点加一个空的队伍
            dataList.add(new TeamListResp());
            teamSize = teamSize + 1;
            length = length + 1;
        }
        // 队列1
        for (int i = 0; i < teamSize / 2; i++) {
            int index = random.nextInt(length);
            TeamListResp resp = dataList.get(index);
            dataList.remove(index);
            teamLinkList1.insertTail(resp.getTeamId());
            length--;
        }
        // 队列2
        for (int i = 0; i < teamSize / 2; i++) {
            int index = random.nextInt(length);
            TeamListResp resp = dataList.get(index);
            dataList.remove(index);
            teamLinkList2.insertHead(resp.getTeamId());
            length--;
        }
        FootballNode next1 = ((FootballLinkList) teamLinkList1).getHead().getNext();
        FootballNode next2 = ((FootballLinkList) teamLinkList2).getHead().getNext();
        for (int i = 0; i < teamSize - 1; i++) {
            for (int k = 0; k < siteIds.size(); k++) {
                siteLinkList1.insertHead(siteIds.get(k));
            }
            BuildEvent(teamSize / 2, eventNodes, next1, next2, i + 1, siteLinkList1);
            if (teamSize == 2) {
                break;
            }
            // 删除2的第一个元素
            FootballNode node2 = teamLinkList2.delete(1);
            // 把删除的元素插入到1的第一个位置
            teamLinkList1.insert(2, node2.getElement());
            // 删除第1的尾部元素
            FootballNode node1 = teamLinkList1.deleteTail();
            // 把尾部元素添加到第2的尾部
            teamLinkList2.insertTail(node1.getElement());
            next1 = ((FootballLinkList) teamLinkList1).getHead().getNext();
            next2 = ((FootballLinkList) teamLinkList2).getHead().getNext();
        }
        return eventNodes;
    }

    public void BuildEvent(int size, List<BuildEvent> eventNodes, FootballNode teamList1, FootballNode teamList2, int batchNo, FootbalList siteLinkList1) {
        for (int i = 0; i < size; i++) {
            BuildEvent buildEvent = new BuildEvent();
            if (teamList1 != null) {
                buildEvent.setTeam1Id(teamList1.getElement() + "");
                teamList1 = teamList1.getNext();
            }

            if (teamList2 != null) {
                buildEvent.setTeam2Id(teamList2.getElement() + "");
                teamList2 = teamList2.getNext();
            }

            FootballNode siteNode = siteLinkList1.deleteHead();
            if (siteNode != null) {
                buildEvent.setSiteId(siteNode.getElement() + "");
            }
            buildEvent.setBatchNo(batchNo);
            eventNodes.add(buildEvent);
        }
    }

    @Override
    public BaseResponse endEvent(String eventId) {
        eventDao.updateStatus(eventId);
        return ResultUtil.OK("更新成功");
    }

    @Transactional
    @Override
    public BaseResponse submitEditeEvent(EditeEventReq editeEventReq) {
        eventDao.submitEditeEvent(editeEventReq);
        if (2 == editeEventReq.getStatus()) { // 赛事结束后给队伍添加积分
            this.seasonIsEnd(editeEventReq.getEventId());
        }
        return ResultUtil.OK("更新成功");
    }

    @Transactional
    @Override
    public BaseResponse seasonIsEnd(String eventId) {
        BaseResponse response = new ObjectResponse<>();
        EventEntity entity = new EventEntity();
        entity.setId(eventId);
        entity.setStatus(2);
        entity.setUpdateTime(new Date());
        this.updateById(entity);
        EventEntity entitys = this.selectById(eventId);
        this.seasonIsEnd(entitys);
        return response.Ok();
    }

    /**
     * 判断该赛季内的赛事是否已经完成
     * @param entity
     */
    public void seasonIsEnd(EventEntity entity){
        Wrapper<EventEntity> wrapper = new EntityWrapper<>();
        Map<String, Object> params = new HashMap<>();
        String eventId = entity.getId(); // 赛事id
        String seasonId = entity.getSeasonId(); // 赛季
        String gradeId = entity.getGradeId(); // 年级
        String classId = entity.getClassId(); // 班级
        Integer category = entity.getCategory(); // 类别
        params.put("eventId", eventId);
        params.put("seasonId", seasonId);
        params.put("gradeId", gradeId);
        params.put("classId", classId);
        // 判断该类别、该年级、该班级、该赛季下出了当前赛事外是否还有赛事没有完成
        int count = 0;
        if (1 == category) { // 天天见
            wrapper.where("category={0} AND grade_id={1} AND season_id={2}",
                    entity.getCategory(), entity.getGradeId(), entity.getSeasonId());
            count = eventDao.getDayEventNotEndCount(params);
        } else if (3 == category) { // 课堂联赛
            wrapper.where("category={0} AND grade_id={1} AND class_id={2} AND season_id={3}",
                    entity.getCategory(), entity.getGradeId(), entity.getClassId(), entity.getSeasonId());
            count = eventDao.getClassRoomEventNotEndCount(params);
        }
        // 如果已经完成 就把赛事里的队员进球数统计进入球队
        if (count == 0) { // 赛季已经完成
            // 查询出所有的赛事
            List<EventEntity> entities = this.selectList(wrapper);
            Map<String, Integer> teamEntityMap = new HashMap<>();
            Map<String, PlayerScoreReq> playerScoreMap = new HashMap<>();
            for (EventEntity event: entities){
                // 计算每个队伍总积分
                this.calculateTeamScore(event, teamEntityMap);
                // 计算每个队成员的总进球数
                this.calculatePlayerScore(event, playerScoreMap);
            }
            // 更新积分
            teamService.batchTeamScore(teamEntityMap);
            // 更新球队队员进球数
            playersService.batchTeamPlayersScore(playerScoreMap);
        }
    }

    /**
     * 计算球队的分数
     * @param event
     * @param teamEntityMap
     */
    public void calculateTeamScore(EventEntity event, Map<String, Integer> teamEntityMap){
        int count1 = event.getTeam1Score();
        int count2 = event.getTeam2Score();
        int team1Score = teamEntityMap.get(event.getTeam1Id()) == null? 0: teamEntityMap.get(event.getTeam1Id());
        int team2Score = teamEntityMap.get(event.getTeam2Id()) == null? 0: teamEntityMap.get(event.getTeam2Id());
        if((count1 != 0 && count2 != 0) && count1 > count2){
            teamEntityMap.put(event.getTeam1Id(), team1Score + 3);
        } else if ((count1 != 0 && count2 != 0) && count1 == count2) {
            teamEntityMap.put(event.getTeam1Id(), team1Score + 1);
            teamEntityMap.put(event.getTeam2Id(), team2Score + 1);
        } else {
            teamEntityMap.put(event.getTeam2Id(), team2Score + 3);
        }
    }

    /**
     * 计算成员的分数
     * @param event
     * @param playerScoreMap
     */
    public void calculatePlayerScore(EventEntity event, Map<String, PlayerScoreReq> playerScoreMap){
        String team1Id = event.getTeam1Id();
        String team2Id = event.getTeam2Id();
        String eventId = event.getId();
        // 查询学生的分数
        List<EventPlayersEntity> entities1 = eventPlayersDao.getPlayerScoreList(eventId, team1Id);
        List<EventPlayersEntity> entities2 = eventPlayersDao.getPlayerScoreList(eventId, team2Id);
        for (EventPlayersEntity entity: entities1){
            String key = eventId + "_" + team1Id + "_" + entity.getStudentId();
            PlayerScoreReq scoreReq = playerScoreMap.get(key);
            if (scoreReq == null) {
                scoreReq = new PlayerScoreReq();
                scoreReq.setScore(0);
                scoreReq.setTeamId(team1Id);
                scoreReq.setStudentId(entity.getStudentId());
            }
            scoreReq.setScore(scoreReq.getScore() + entity.getScore());
            playerScoreMap.put(key, scoreReq);
        }
        for (EventPlayersEntity entity: entities2){
            String key = eventId + "_" + team2Id+ "_" + entity.getStudentId();
            PlayerScoreReq scoreReq = playerScoreMap.get(key);
            if (scoreReq == null) {
                scoreReq = new PlayerScoreReq();
                scoreReq.setScore(0);
                scoreReq.setTeamId(team2Id);
                scoreReq.setStudentId(entity.getStudentId());
            }
            scoreReq.setScore(scoreReq.getScore() + entity.getScore());
            playerScoreMap.put(key, scoreReq);
        }

    }



    @Override
    public List<EventEntity> showTodayEvent() {
        return eventDao.showTodayEvent();
    }


}
