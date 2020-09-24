package com.sjht.school.football.service.impl.mobile;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.football.dao.football.event.EventDao;
import com.sjht.school.football.req.mobile.GetBatchNoListReq;
import com.sjht.school.football.service.mobile.MobileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ***************************************************
 *
 * @ClassName MobileCommonServiceImpl
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/29 10:39
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class MobileCommonServiceImpl implements MobileCommonService {

    @Autowired
    private EventDao eventDao;

    @Override
    public BaseResponse getBatchNoList(GetBatchNoListReq batchNoListReq) {
        ObjectResponse response = new ObjectResponse();
        return response.OK(eventDao.getBatchNoList(batchNoListReq));
    }
}
