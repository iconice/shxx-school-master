package com.sjht.school.football.controller.mobile;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.football.req.sys.LoginUserReq;
import com.sjht.school.football.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ***************************************************
 *
 * @ClassName SysMobileController
 * @Description 系统相关控制层
 * @Author 张弛
 * @Date 2019/10/24 11:42
 * @Version V1.0
 * ****************************************************
 **/
@Controller
@RequestMapping("/sysMobile/user")
public class SysMobileController {
    @Autowired SysUserService sysUserService;
    /**
     * 提交登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public BaseResponse login(String username, String password, String verification) {
        LoginUserReq loginUserReq = new LoginUserReq(username, password, verification);
        BaseResponse baseResponse = sysUserService.loginUser(loginUserReq);
        return baseResponse;
    }
}
