package com.sjht.school.mobile.controller;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.common.entity.ResultUtil;
import com.sjht.school.football.req.mobile.GetBatchNoListReq;
import com.sjht.school.football.req.mobile.MobileLoginReq;
import com.sjht.school.football.resp.football.student.GetClassListResp;
import com.sjht.school.football.resp.mobile.MobileLoginResp;
import com.sjht.school.football.service.common.TokenService;
import com.sjht.school.football.service.mobile.MobileLoginService;
import com.sjht.school.football.service.mobile.MobileUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ***************************************************
 *
 * @ClassName LoginController
 * @Description 手机端用户登录aip
 * @Author maojianyun
 * @Date 2019/10/28 9:30
 * @Version V1.0
 * ****************************************************
 **/
@RestController
@RequestMapping("login")
@Api(value = "登录接口api", tags = "移动端-登录接口api", description = "移动端-登录接口api")
public class MobileLoginController {

    @Autowired
    private MobileLoginService mobileLoginService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/mobileLogin")
    @ApiOperation(value = "移动端用户登录", notes = "登录参数")
    @ApiParam(name = "mobileLoginReq", value = "登录请求实体", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000005, message = "登录失败")})
    public BaseResponse mobileLogin(@RequestBody MobileLoginReq loginReq) {
        return mobileLoginService.mobileLogin(loginReq);
    }

    @GetMapping("getMobileUserInfo")
    @ApiOperation(value = "移动端得到用户信息", notes = "token")
    @ApiParam(name = "token", value = "token", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000007, message = "登录过期"),
            @ApiResponse(code = 100000004, message = "失败")})
    @ResponseBody
    public ObjectResponse getMobileUserInfo(String token) {
        return mobileLoginService.getMobileUser(token);
    }

    @GetMapping("/mobileLogout")
    @ApiOperation(value = "移动端用户退出登录", notes = "")
    @ApiParam(name = "token", value = "token", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000004, message = "失败")})
    public Boolean mobileLogout(String token) {
        // return mobileLoginService.mobileLogout(token);
        return tokenService.deleteTokenByToken(token);
    }

}
