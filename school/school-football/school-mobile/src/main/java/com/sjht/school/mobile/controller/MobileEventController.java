package com.sjht.school.mobile.controller;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.football.req.football.event.EditeEventReq;
import com.sjht.school.football.req.football.event.MobileEventListReq;
import com.sjht.school.football.req.football.team.UpdatePlayersScoreReq;
import com.sjht.school.football.req.mobile.MobileLoginReq;
import com.sjht.school.football.service.football.event.EventPlayersService;
import com.sjht.school.football.service.football.event.EventService;
import com.sjht.school.football.service.mobile.MobileEventService;
import com.sjht.school.football.service.mobile.MobileLoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ***************************************************
 *
 * @ClassName MobileEventController
 * @Description 描述getMobileEventList
 * @Author 张弛
 * @Date 2019/10/29 15:46
 * @Version V1.0
 * ****************************************************
 **/
@RestController
@RequestMapping("event")
@Api(value = "赛事接口api", tags = "移动端-赛事接口api", description = "移动端-赛事接口api")
public class MobileEventController {

    @Autowired
    private EventPlayersService eventPlayersService;

    @Autowired
    private EventService eventService;

    @Autowired
    private MobileEventService mobileEventService;

    @PostMapping("/getMobileEventList")
    @ApiOperation(value = "获取移动端赛事列表", notes = "参数")
    @ApiParam(name = "MobileEventListReq", value = "赛事请求实体", required = true)
    public BaseResponse getMobileEventList(@RequestBody MobileEventListReq mobileEventListReq){
        return mobileEventService.getMobileEventList(mobileEventListReq);
    }
//eventID  赛事Id
    @PostMapping("/getMobileEventInfoById")
    @ApiOperation(value = "获取移动端赛事详情", notes = "参数")
    @ApiParam(name = "id", value = "赛事请求ID", required = true)
    public BaseResponse getMobileEventInfoById(String id){
        return mobileEventService.getMobileEventInfo(id);
    }

    @PostMapping("/updatePlayersScore")
    @ApiOperation(value = "移动端分数加减", notes = "分数加减参数")
    @ApiParam(name = "updatePlayersScoreReq", value = "分数加减请求实体", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000005, message = "失败")})
    public BaseResponse updatePlayersScore(UpdatePlayersScoreReq playersScoreReq){
        return eventPlayersService.updatePlayersScore(playersScoreReq);
    }

    @GetMapping("endEvent")
    @ApiOperation(value = "移动端结束赛事", notes = "结束赛事参数")
    @ApiParam(name = "updatePlayersScoreReq", value = "结束赛事请求实体", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000005, message = "失败")})
    public BaseResponse endEvent(String enventId){
        return eventService.seasonIsEnd(enventId);
    }
}
