package com.sjht.school.football.resp.football.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 *
 * @ClassName MobileEventInfoResp
 * @Description 赛事详情
 * @Author 张弛
 * @Date 2019/10/30 13:47
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "MobileEventInfoResp", description = "移动端赛事信息请求实体")
public class MobileEventInfoResp implements Serializable {
    @ApiModelProperty(name = "id", value = "赛事id")
    private String id;//赛事ID
    @ApiModelProperty(name = "team1Id", value = "球队1ID")
    private String team1Id;//球队1ID
    @ApiModelProperty(name = "team1Id", value = "球队2ID")
    private String team2Id;//球队2ID
    @ApiModelProperty(name = "team1Name", value = "球队1")
    private String team1Name;//球队1
    @ApiModelProperty(name = "team2Name", value = "球队2")
    private String team2Name;//球队2
    @ApiModelProperty(name = "gradeId", value = "届")
    private String gradeId;//届
    @ApiModelProperty(name = "className", value = "年级名")
    private String className;//年级名
    @ApiModelProperty(name = "eventName", value = "赛事名")
    private String eventName;//赛事名
    @ApiModelProperty(name = "category", value = "赛事栏目")
    private String category;//赛事栏目
    @ApiModelProperty(name = "status", value = "赛事状态")
    private String status;// 赛事是否结束   0结束 1没有
    @ApiModelProperty(name = "team1Score", value = "球队1分数")
    private String team1Score; //球队1分数
    @ApiModelProperty(name = "team2Score", value = "team2Score")
    private String team2Score; //球队2分数
    @ApiModelProperty(name = "batchNo", value = "轮次")
    private String batchNo; //轮次
    @ApiModelProperty(name = "siteName", value = "场地名")
    private String siteName; //场地信息
    @ApiModelProperty(name = "season", value = "赛季")
    private String season; //赛季


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
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

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
