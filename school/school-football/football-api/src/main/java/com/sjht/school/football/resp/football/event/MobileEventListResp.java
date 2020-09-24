package com.sjht.school.football.resp.football.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 *
 * @ClassName MobileEventListResp
 * @Description 描述
 * @Author 张弛
 * @Date 2019/10/30 13:47
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "MobileEventListResp", description = "移动端赛事信息请求实体")
public class MobileEventListResp implements Serializable {
    @ApiModelProperty(name = "id", value = "赛事id")
    private String id;//赛事ID
    @ApiModelProperty(name = "team1Name", value = "球队1")
    private String team1Name;//球队1
    @ApiModelProperty(name = "team2Name", value = "球队2")
    private String team2Name;//球队2
    @ApiModelProperty(name = "eventName", value = "赛事名")
    private String eventName;//赛事名
    @ApiModelProperty(name = "category", value = "赛事栏目")
    private String category;//赛事栏目
    @ApiModelProperty(name = "status", value = "赛事状态")
    private String status;// 赛事是否结束   0结束 2没

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
