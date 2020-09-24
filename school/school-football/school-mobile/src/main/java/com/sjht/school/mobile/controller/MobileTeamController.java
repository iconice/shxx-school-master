package com.sjht.school.mobile.controller;

import com.sjht.school.common.entity.BaseResponse;

import com.sjht.school.football.req.football.event.MobileEventListReq;
import com.sjht.school.football.req.football.team.MobileTeamReq;
import com.sjht.school.football.service.mobile.MobileTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

/**
 * ***************************************************
 *
 * @ClassName MobileTeamController
 * @Description 描述
 * @Author 张弛
 * @Date 2019/10/29 15:35
 * @Version V1.0
 * ****************************************************
 **/
@RestController
@RequestMapping("team")
@Api(value = "球队接口api", tags = "移动端-球队接口api", description = "移动端-球队接口api")
public class MobileTeamController {
        @Autowired
        MobileTeamService mobileTeamService;

    @PostMapping("/getMobileTeamPlayer")
    @ApiOperation(value = "获取移动端球队球员信息", notes = "")
    @ApiParam(name = "", value = "登录请求实体", required = true)
    public BaseResponse getMobileEventInfo(@RequestBody MobileTeamReq mobileTeamReq){
            return mobileTeamService.getMobileTeamPlayerInfo(mobileTeamReq);
    }

}
