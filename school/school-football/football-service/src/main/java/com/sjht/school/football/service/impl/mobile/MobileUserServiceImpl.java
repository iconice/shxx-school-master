package com.sjht.school.football.service.impl.mobile;

import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.football.dao.sys.SysUserDao;
import com.sjht.school.football.resp.mobile.GetMobileUserResp;
import com.sjht.school.football.service.common.TokenService;
import com.sjht.school.football.service.mobile.MobileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ***************************************************
 *
 * @ClassName MobileUserService
 * @Description 移动端用户服务
 * @Author 张弛
 * @Date 2019/10/29 10:20
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class MobileUserServiceImpl implements MobileUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MobileUserService mobileUserService;

    @Override
    public ObjectResponse mobileUser(String token) {
        ObjectResponse response = new ObjectResponse();
        GetMobileUserResp userResp = sysUserDao.getMobileUserInfo(token);
        Date currentDate = new Date();
        return response;
    }
}
