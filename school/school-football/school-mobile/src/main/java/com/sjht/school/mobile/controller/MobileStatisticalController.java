package com.sjht.school.mobile.controller;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.PageResult;
import com.sjht.school.football.req.football.statistical.GetScoreboarListReq;
import com.sjht.school.football.req.football.statistical.GetSeasonLeaderboardListReq;
import com.sjht.school.football.req.football.statistical.GetTopScorerListReq;
import com.sjht.school.football.service.common.SeasonService;
import com.sjht.school.football.service.football.student.GradeService;
import com.sjht.school.football.service.mobile.MobileStatisticalService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ***************************************************
 * @ClassName MobileStatisticalController
 * @Description 积分统计
 * @Author maojianyun
 * @Date 2019/11/19 13:55
 * @Version V1.0
 * ****************************************************
 **/
@RestController
@RequestMapping("/mobileStatistical")
@Api(value = "积分、射手榜接口api", tags = "积分、射手榜接口api", description = "积分、射手榜接口api")
public class MobileStatisticalController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private MobileStatisticalService statisticalService;

    @Autowired
    private SeasonService seasonService;

    @PostMapping("event/getTopScorerList")
    @ApiOperation(value = "得到射手榜列表", notes = "参数")
    @ApiParam(name = "topScorerListReq", value = "登录请求实体", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000005, message = "登录失败")})
    @ResponseBody
    public BaseResponse getTopScorerList(@RequestBody GetTopScorerListReq topScorerListReq){
        return statisticalService.getTopScorerList(topScorerListReq);
    }

    /**
     *
     * @param getScoreboarListReq
     * @return
     */
    @PostMapping("event/getScoreboardList")
    @ApiOperation(value = "得到积分榜列表", notes = "参数")
    @ApiParam(name = "getScoreboarListReq", value = "得到积分榜列表", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000004, message = "失败")})
    @ResponseBody
    public BaseResponse getScoreboardList(@RequestBody GetScoreboarListReq getScoreboarListReq){
        return statisticalService.getScoreboardList(getScoreboarListReq);
    }

    @PostMapping("event/getSeasonLeaderboardList")
    @ApiOperation(value = "得到赛季排行榜列表", notes = "参数")
    @ApiParam(name = "scoreboarListReq", value = "得到赛季排行榜列表", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000004, message = "失败")})
    @ResponseBody
    public BaseResponse getSeasonLeaderboardList(@RequestBody GetSeasonLeaderboardListReq getSeasonLeaderboardListReq){
        return statisticalService.getSeasonLeaderboardList(getSeasonLeaderboardListReq);
    }

    @PostMapping("event/getSeasonScoreboardList")
    @ApiOperation(value = "得到赛季积分榜列表", notes = "参数")
    @ApiParam(name = "scoreboarListReq", value = "得到赛季积分榜列表", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000004, message = "失败")})
    @ResponseBody
    public BaseResponse getSeasonScoreboardList(@RequestBody GetSeasonLeaderboardListReq getSeasonLeaderboardListReq){
        return statisticalService.getSeasonScoreboardList(getSeasonLeaderboardListReq);
    }
}
