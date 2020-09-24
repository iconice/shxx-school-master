package com.sjht.school.football.service.impl.mobile;

import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.football.dao.football.team.TeamDao;
import com.sjht.school.football.dao.football.team.TeamPlayersDao;
import com.sjht.school.football.req.football.team.MobileTeamReq;
import com.sjht.school.football.resp.football.team.MobileTeamResp;
import com.sjht.school.football.service.mobile.MobileTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ***************************************************
 *
 * @ClassName MobileTeamServiceImpl
 * @Description 描述
 * @Author 张弛
 * @Date 2019/10/30 10:51
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class MobileTeamServiceImpl implements MobileTeamService {
    @Autowired
    private TeamPlayersDao teamPlayersDao;

    @Override
    public ObjectResponse getMobileTeamPlayerInfo(MobileTeamReq mobileTeamReq) {
        ObjectResponse response =new ObjectResponse();
        List<MobileTeamResp> teamResps= teamPlayersDao.getMobilePlayersList(mobileTeamReq);
        response.OK(teamResps);
        return response;
    }
}
