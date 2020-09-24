package com.sjht.school.mobile.controller;

import com.sjht.school.common.entity.BaseResponse;
import com.sjht.school.common.entity.ObjectResponse;
import com.sjht.school.common.enums.RespCodeEnums;
import com.sjht.school.football.req.mobile.GetBatchNoListReq;
import com.sjht.school.football.resp.football.student.GetClassListResp;
import com.sjht.school.football.resp.football.student.GradeResp;
import com.sjht.school.football.service.common.SeasonService;
import com.sjht.school.football.service.football.student.ClassService;
import com.sjht.school.football.service.football.student.GradeService;
import com.sjht.school.football.service.mobile.MobileCommonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ***************************************************
 * @ClassName MobileCommonController
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/29 9:59
 * @Version V1.0
 * ****************************************************
 **/
@RestController
@RequestMapping("common")
@Api(value = "公共接口api", tags = "移动端-公共api", description = "移动端-公共api")
public class MobileCommonController {

    @Autowired
    private MobileCommonService commonService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ClassService classService;

    @Autowired
    private SeasonService seasonService;


    @GetMapping("/getGradeList")
    @ApiOperation(value = "移动端得到年级列表", notes = "用户id")
    @ApiParam(name = "userId", value = "用户id", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000004, message = "失败")})
    @ResponseBody
    public ObjectResponse getGrades(String userId) {
        ObjectResponse gradeResps = gradeService.getgradeList(userId);
        List<GradeResp> data = (List<GradeResp>)gradeResps.getData();
        return gradeResps;
    }

    @GetMapping("getClassList")
    @ApiOperation(value = "移动端得到班级列表", notes = "年级id")
    @ApiParam(name = "gradeId", value = "年级id", required = true)
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000004, message = "失败")})
    @ResponseBody
    public ObjectResponse getClassList(String gradeId) {
        ObjectResponse response = new ObjectResponse();
        Map<String, Object> params = new HashMap<>();
        List<GetClassListResp> classList = new ArrayList<>();
        params.put("gradeId", gradeId);
        List<GetClassListResp> data = classService.getClassList(params);
        response.OK(classService.getClassList(params));
        return response;
    }

    @GetMapping("getSeasonList")
    @ApiOperation(value = "移动端得到赛季列表")
    @ApiResponses({@ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000004, message = "失败")})
    @ResponseBody
    public ObjectResponse getSeasonList() {
        ObjectResponse response = new ObjectResponse();
        response.OK(seasonService.getSeasonsByYearAndTerm());
        return response;
    }

    @PostMapping("/getBatchNoList")
    @ApiOperation(value = "移动端得到轮次", notes = "轮次参数")
    @ApiParam(name = "getBatchNoListReq", value = "查询轮次请求实体", required = true)
    @ApiResponses({ @ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000005, message = "失败")})
    public BaseResponse getBatchNoList(@RequestBody GetBatchNoListReq batchNoListReq){
        return  commonService.getBatchNoList(batchNoListReq);
    }

    @GetMapping("/overdue")
    @ApiOperation(value = "移动端用户过期返回")
    @ApiResponses({ @ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000005, message = "失败")})
    public BaseResponse overdue(){
        BaseResponse response = new BaseResponse();
        response.error(RespCodeEnums.LOGIN_EXPIRATION_CODE.getCode(), RespCodeEnums.LOGIN_EXPIRATION_CODE.getMsg());
        return  response;
    }

    @GetMapping("/tokenIsNull")
    @ApiOperation(value = "移动端没有拿到token")
    @ApiResponses({ @ApiResponse(code = 100000001, message = "服务器出错", response = BaseResponse.class),
            @ApiResponse(code = 100000002, message = "不支持该请求方式", response = BaseResponse.class),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000003, message = "成功"),
            @ApiResponse(code = 100000005, message = "登录失败")})
    public BaseResponse tokenIsNull(){
        BaseResponse response = new BaseResponse();
        response.error(RespCodeEnums.TOKEN_IS_NULL_CODE.getCode(), RespCodeEnums.TOKEN_IS_NULL_CODE.getMsg());
        return  response;
    }
}
