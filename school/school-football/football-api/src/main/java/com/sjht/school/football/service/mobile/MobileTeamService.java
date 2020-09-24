package com.sjht.school.football.service.mobile;

import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.football.req.football.event.MobileEventListReq;
import com.sjht.school.football.req.football.team.MobileTeamReq;

/**
 * ***************************************************
 *
 * @ClassName MobileTeamService
 * @Description 移动端球队服务
 * @Author 张弛
 * @Date 2019/10/30 14:55
 * @Version V1.0
 * ****************************************************
 **/
public interface MobileTeamService {
    ObjectResponse getMobileTeamPlayerInfo(MobileTeamReq mobileTeamReq);
}
