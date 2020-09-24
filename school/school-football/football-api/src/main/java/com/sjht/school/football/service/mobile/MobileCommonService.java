package com.sjht.school.football.service.mobile;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.football.req.mobile.GetBatchNoListReq;

/**
 * ***************************************************
 *
 * @ClassName MobileCommonService
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/29 10:39
 * @Version V1.0
 * ****************************************************
 **/
public interface MobileCommonService {

    /**
     * 得到轮次列表
     * @param batchNoListReq
     * @return
     */
    BaseResponse getBatchNoList(GetBatchNoListReq batchNoListReq);
}
