package com.sjht.school.football.service.impl.mobile;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.common.entity.PageResult;
import com.sjht.school.common.enums.RespCodeEnums;
import com.sjht.school.common.utils.PageUtil;
import com.sjht.school.football.dao.football.event.EventDao;
import com.sjht.school.football.req.football.event.MobileEventListReq;
import com.sjht.school.football.resp.football.event.MobileEventInfoResp;
import com.sjht.school.football.resp.football.event.MobileEventListResp;
import com.sjht.school.football.resp.football.event.QueryEventPageListResp;
import com.sjht.school.football.service.mobile.MobileEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ***************************************************
 *
 * @ClassName MobileEventServiceImpl
 * @Description 赛事服务实现
 * @Author 张弛
 * @Date 2019/10/30 14:58
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class MobileEventServiceImpl implements MobileEventService {
    @Autowired
    EventDao eventDao;

    @Override
    public ObjectResponse getMobileEventList(MobileEventListReq mobileEventListReq) {
        Integer pageNo = Integer.valueOf(PageUtil.getPageNo(mobileEventListReq.getLimit(), mobileEventListReq.getOffset()));
        Integer pageSize = Integer.valueOf(mobileEventListReq.getLimit());
        Page<MobileEventListResp> page = new Page<>(pageNo, pageSize);
        ObjectResponse response = new ObjectResponse();

        //判断栏目的类型 如果为1（天天见）不展示班级信息
        int category = mobileEventListReq.getCategory();
        if (1 == category) {
            List<MobileEventListResp> eventInfoList = eventDao.mobileShowEvent1(page, mobileEventListReq);
            page.setRecords(eventInfoList);
            response.OK(page);
        }
            if(3 == category){
                //课堂联赛直接查询
                List<MobileEventListResp> eventInfoList= eventDao.mobileShowEvent2(page,mobileEventListReq);
                page.setRecords(eventInfoList);
                response.OK(page);
            }
        return response;
    }

    @Override
    public ObjectResponse getMobileEventInfo(String id) {
        ObjectResponse response=new ObjectResponse();
        List<MobileEventInfoResp> eventInfo = eventDao.mobileShowEventInfo(id);
        response.OK(eventInfo);
        return response;
    }
}
