package com.sjht.school.football.service.mobile;

import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.football.req.mobile.MobileLoginReq;

/**
 * ***************************************************
 *
 * @ClassName MobileLoginService
 * @Description 移动端登录接口
 * @Author maojianyun
 * @Date 2019/10/28 9:36
 * @Version V1.0
 * ****************************************************
 **/
public interface MobileLoginService {

    /**
     * 登录
     * @param loginReq
     * @return
     */
    ObjectResponse mobileLogin(MobileLoginReq loginReq);

    /**
     * 得到用户信息
     * @param token
     * @return
     */
    public ObjectResponse getMobileUser(String token);



}
