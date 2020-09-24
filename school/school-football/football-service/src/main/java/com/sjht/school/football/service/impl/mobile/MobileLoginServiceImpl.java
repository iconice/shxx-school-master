package com.sjht.school.football.service.impl.mobile;

import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.common.enums.RespCodeEnums;
import com.sjht.school.common.utils.DateUtils;
import com.sjht.school.common.utils.IdUtil;
import com.sjht.school.football.dao.sys.SysUserDao;
import com.sjht.school.football.entity.common.TokenEntity;
import com.sjht.school.football.req.mobile.MobileLoginReq;
import com.sjht.school.football.resp.mobile.GetMobileUserResp;
import com.sjht.school.football.resp.mobile.MobileLoginResp;
import com.sjht.school.football.service.common.TokenService;
import com.sjht.school.football.service.mobile.MobileLoginService;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * ***************************************************
 * @ClassName MobileLoginServiceImpl
 * @Description 移动端用户登录
 * @Author maojianyun
 * @Date 2019/10/28 9:37
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class MobileLoginServiceImpl implements MobileLoginService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private TokenService tokenService;

    @Transactional
    @Override
    public ObjectResponse mobileLogin(MobileLoginReq loginReq) {
        ObjectResponse response = new ObjectResponse();
        loginReq.setPassword(SecureUtil.md5(loginReq.getPassword()));
        MobileLoginResp loginResp = sysUserDao.mobileLogin(loginReq);
        if (loginResp != null) {
            TokenEntity entity = new TokenEntity();
            String token = UUID.randomUUID().toString().replace("-", "");
            entity.setId(IdUtil.getId());
            entity.setUserId(loginResp.getUserId());
            entity.setToken(token);
            entity.setExpirationTime(DateUtils.getInstance().stepDay(new Date(), 30));
            tokenService.deleteTokenByuserId(loginResp.getUserId());
            boolean b = tokenService.insetToken(entity);
            response.OK(token);
        } else {
            response.error(RespCodeEnums.LONGIN_FAILURE_CODE.getCode(), RespCodeEnums.LONGIN_FAILURE_CODE.getMsg());
        }
        return response;
    }

    @Override
    public ObjectResponse getMobileUser(String token) {
        ObjectResponse response = new ObjectResponse();
        GetMobileUserResp userResp = sysUserDao.getMobileUserInfo(token);
        if (userResp != null) {
            int status =  Integer.parseInt(userResp.getStatus());
            int del =  Integer.parseInt(userResp.getDel());
            long currDate = DateUtils.getInstance().dateToStamp(new Date());//获取当前时间
            long expirationDate = DateUtils.getInstance().dateToStamp(userResp.getExpirationTime());//获取过期时间
            if (currDate < expirationDate && 1==status && 0==del) {
                response.OK(userResp);
            } else {
                response.error(RespCodeEnums.LOGIN_EXPIRATION_CODE.getCode(), RespCodeEnums.LOGIN_EXPIRATION_CODE.getMsg());
            }
        } else {
            response.error(RespCodeEnums.LOGIN_EXPIRATION_CODE.getCode(), RespCodeEnums.LOGIN_EXPIRATION_CODE.getMsg());
        }
        return response;
    }
}
