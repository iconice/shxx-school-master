package com.sjht.school.football.req.football.team;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 *
 * @ClassName MobileTeamReq
 * @Description 描述
 * @Author 张弛
 * @Date 2019/10/30 9:50
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "MobileTeamReq", description = "根据球队ID获取成员")
public class MobileTeamReq implements Serializable {
    @ApiModelProperty(name = "teamId", value = "球队ID")
    private String teamId;
    @ApiModelProperty(name = "eventId", value = "赛事ID")
    private String eventId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}