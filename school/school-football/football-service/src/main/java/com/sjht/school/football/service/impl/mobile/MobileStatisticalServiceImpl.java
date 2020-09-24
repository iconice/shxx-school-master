package com.sjht.school.football.service.impl.mobile;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.common.entity.PageResult;
import com.sjht.school.common.utils.PageUtil;
import com.sjht.school.football.dao.football.event.EventDao;
import com.sjht.school.football.dao.football.event.EventPlayersDao;
import com.sjht.school.football.dao.football.student.StudentDao;
import com.sjht.school.football.dao.football.team.TeamDao;
import com.sjht.school.football.entity.football.event.EventEntity;
import com.sjht.school.football.req.football.statistical.GetScoreboarListReq;
import com.sjht.school.football.req.football.statistical.GetSeasonLeaderboardListReq;
import com.sjht.school.football.req.football.statistical.GetTopScorerListReq;
import com.sjht.school.football.resp.football.statistical.GetScoreboarListResp;
import com.sjht.school.football.resp.football.statistical.GetSeasonLeaderboardListResp;
import com.sjht.school.football.resp.football.statistical.GetSeasonScoreboardList;
import com.sjht.school.football.resp.football.statistical.GetTopScorerListResp;
import com.sjht.school.football.service.mobile.MobileStatisticalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ***************************************************
 *
 * @ClassName MobileStatisticalServiceImpl
 * @Description 移动端统计
 * @Author maojianyun
 * @Date 2019/11/21 10:30
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class MobileStatisticalServiceImpl implements MobileStatisticalService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private EventPlayersDao eventPlayersDao;

    @Autowired
    private EventDao eventDao;

    @Override
    public BaseResponse getTopScorerList(GetTopScorerListReq topScorerListReq) {
        ObjectResponse response = new ObjectResponse<>();
        Map<String, Object> params = new HashMap<>();
        Integer pageNo = Integer.valueOf(PageUtil.getPageNo(topScorerListReq.getLimit(), topScorerListReq.getOffset()));
        Integer pageSize = Integer.valueOf(topScorerListReq.getLimit());
        Page<GetTopScorerListResp> page = new Page<>(pageNo, pageSize);
        if(StringUtils.isNotBlank(topScorerListReq.getGradeId())){
            params.put("gradeId", topScorerListReq.getGradeId());
        }
        if(StringUtils.isNotBlank(topScorerListReq.getClassId())){
            params.put("classId", topScorerListReq.getClassId());
        }

        params.put("teamType", topScorerListReq.getTeamType());

        long count = 0;
        List<GetTopScorerListResp> datas = new ArrayList<>();
        if (topScorerListReq.getTeamType() == 1) {
            count = studentDao.getDayTopScorerListCount(params);
            if (count > 0) {
                page.setRecords(studentDao.getDayTopScorerList(page, params));
                page.setTotal(count);
            }
        } else {
            count = studentDao.getClassRoomTopScorerListCount(params);
            if (count > 0) {
                page.setRecords(studentDao.getClassRoomTopScorerList(page, params));
                page.setTotal(count);
            }
        }
        return response.OK(page);
    }

    @Override
    public BaseResponse getScoreboardList(GetScoreboarListReq scoreboarListReq) {
        ObjectResponse response = new ObjectResponse<>();
        Map<String, Object> params = new HashMap<>();
        Integer pageNo = Integer.valueOf(PageUtil.getPageNo(scoreboarListReq.getLimit(), scoreboarListReq.getOffset()));
        Integer pageSize = Integer.valueOf(scoreboarListReq.getLimit());
        Page<GetScoreboarListResp> page = new Page<>(pageNo, pageSize);
        if(StringUtils.isNotBlank(scoreboarListReq.getGradeId())){
            params.put("gradeId", scoreboarListReq.getGradeId());
        }
        if(StringUtils.isNotBlank(scoreboarListReq.getClassId())){
            params.put("classId", scoreboarListReq.getClassId());
        }
        long count = 0;
        List<GetScoreboarListResp> datas = new ArrayList<>();
        if (scoreboarListReq.getTeamType() == 1) {
            count = teamDao.getDayScoreboardListCount(params);
            if (count > 0) {
                page.setRecords(teamDao.getDayScoreboardList(page, params));
                page.setTotal(count);
            }
        } else {
            count = teamDao.getClassRoomScoreboardListCount(params);
            if (count > 0) {
                page.setRecords(teamDao.getClassRoomScoreboardList(page, params));
                page.setTotal(count);
            }
        }
        return response.OK(page);
    }

    @Override
    public BaseResponse getSeasonLeaderboardList(GetSeasonLeaderboardListReq listReq) {
        ObjectResponse response = new ObjectResponse<>();
        Map<String, Object> params = new HashMap<>();
        Integer pageNo = Integer.valueOf(PageUtil.getPageNo(listReq.getLimit(), listReq.getOffset()));
        Integer pageSize = Integer.valueOf(listReq.getLimit());
        Page<GetSeasonLeaderboardListResp> page = new Page<>(pageNo, pageSize);
        if(StringUtils.isNotBlank(listReq.getGradeId())){
            params.put("gradeId", listReq.getGradeId());
        }
        if(StringUtils.isNotBlank(listReq.getClassId())){
            params.put("classId", listReq.getClassId());
        }
        params.put("category", listReq.getCategory());
        params.put("seasonId", listReq.getSeasonId());
        long count = eventPlayersDao.getSeasonLeaderboardListCount(params);
        List<GetSeasonLeaderboardListResp> datas = new ArrayList<>();
        if (count > 0) {
            List<GetSeasonLeaderboardListResp> list = eventPlayersDao.getSeasonLeaderboardList(page, params);
            for(GetSeasonLeaderboardListResp resp: list){
                params.put("studentId", resp.getStudentId());
                String batchNo = eventPlayersDao.getBatchNo(params);
                resp.setBatchNo(batchNo == null? 0: Integer.valueOf(batchNo));
            }
            page.setRecords(list);
            page.setTotal(count);
        }
        return response.OK(page);
    }

    @Override
    public BaseResponse getSeasonScoreboardList(GetSeasonLeaderboardListReq listReq) {
        ObjectResponse response = new ObjectResponse<>();
        Map<String, GetSeasonScoreboardList> teams = new HashMap<>();
        PageResult pageResult = new PageResult();
        Map<String, Object> params = new HashMap<>();
        Integer pageNo = Integer.valueOf(PageUtil.getPageNo(listReq.getLimit(), listReq.getOffset()));
        Integer pageSize = Integer.valueOf(listReq.getLimit());
        Page<GetSeasonScoreboardList> page = new Page<>(pageNo, pageSize);
        if(StringUtils.isNotBlank(listReq.getGradeId())){
            params.put("gradeId", listReq.getGradeId());
        }
        if(StringUtils.isNotBlank(listReq.getClassId())){
            params.put("classId", listReq.getClassId());
        }
        params.put("category", listReq.getCategory());
        params.put("teamType", listReq.getCategory());
        params.put("seasonId", listReq.getSeasonId());
        long count = teamDao.getSeasonTeamListCount(params);
        List<GetSeasonScoreboardList> datas = new ArrayList<>();
        if (count > 0) {
            List<GetSeasonScoreboardList> lists = teamDao.getSeasonTeamList(page, params);
            for (GetSeasonScoreboardList scoreboardList: lists){
                teams.put(scoreboardList.getTeamId(), scoreboardList);
            }
            List<EventEntity> entities = eventDao.getfinshEventList(params);
            page.setRecords(getSeasonScoreboardList(teams, entities));
            page.setTotal(count);
        }
        return response.OK(page);
    }

    public List<GetSeasonScoreboardList> getSeasonScoreboardList(Map<String, GetSeasonScoreboardList> teams, List<EventEntity> entities){
        List<GetSeasonScoreboardList> lists = new ArrayList<>();
        for (EventEntity entity: entities){
            String team1Id = entity.getTeam1Id();
            String team2Id = entity.getTeam2Id();
            GetSeasonScoreboardList scoreboardList1 = teams.get(team1Id);
            GetSeasonScoreboardList scoreboardList2 = teams.get(team2Id);
            if (StringUtils.isNotBlank(team1Id) && StringUtils.isNotBlank(team2Id)
                    && scoreboardList1 != null && scoreboardList2 != null) {
                int score1 = entity.getTeam1Score();
                int score2 = entity.getTeam2Score();
                if (score1 > score2) {
                    scoreboardList1.setScore(scoreboardList1.getScore() + 3);
                    scoreboardList1.setBatchNo(entity.getBatchNo());
                    teams.put(team1Id, scoreboardList1);
                    scoreboardList2.setBatchNo(entity.getBatchNo());
                    teams.put(team2Id, scoreboardList2);
                } else if (score1 < score2){
                    scoreboardList2.setScore(scoreboardList2.getScore() + 3);
                    scoreboardList2.setBatchNo(entity.getBatchNo());
                    teams.put(team2Id, scoreboardList2);
                    scoreboardList1.setBatchNo(entity.getBatchNo());
                    teams.put(team1Id, scoreboardList1);
                } else {
                    scoreboardList1.setScore(scoreboardList1.getScore() + 1);
                    scoreboardList1.setBatchNo(entity.getBatchNo());
                    scoreboardList2.setScore(scoreboardList2.getScore() + 1);
                    scoreboardList2.setBatchNo(entity.getBatchNo());
                    teams.put(team1Id, scoreboardList1);
                    teams.put(team2Id, scoreboardList2);
                }
            }
        }
        for(Map.Entry<String, GetSeasonScoreboardList> entry: teams.entrySet()){
            lists.add(entry.getValue());
        }
        lists.sort((u1, u2) -> u2.getScore().compareTo(u1.getScore()));
        return lists;
    }
}
