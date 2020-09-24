package com.sjht.school.football.service.mobile;

import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.football.req.football.event.MobileEventListReq;

/**
 * ***************************************************
 *
 * @ClassName MobileEventService
 * @Description 移动端赛事服务
 * @Author 张弛
 * @Date 2019/10/30 14:55
 * @Version V1.0
 * ****************************************************
 **/
public interface MobileEventService {
    //赛事查询列表信息
    ObjectResponse getMobileEventList(MobileEventListReq mobileEventListReq);
    //赛事详情信息
    ObjectResponse getMobileEventInfo(String id);
}
