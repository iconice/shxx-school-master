package com.sjht.school.football.req.football.team;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 * @ClassName UpdatePlayersScoreReq
 * @Description 更新个人的分数
 * @Author maojianyun
 * @Date 2019/9/14 20:01
 * @Version V1.0
 * ****************************************************
 **/

@ApiModel(value = "UpdatePlayersScoreReq", description = "更新分数请求实体")
public class UpdatePlayersScoreReq implements Serializable {

    @ApiModelProperty(name = "id", value = "赛事队员的id")
    private String id;

    @ApiModelProperty(name = "studentId", value = "球员id")
    private String studentId;

    @ApiModelProperty(name = "operation", value = "假球为：+，减球为：-")
    private String operation;

    @ApiModelProperty(name = "eventId", value = "赛事id")
    private String eventId;

    @ApiModelProperty(name = "team", value = "team为1就是1队加减分，为2就是2队加减分")
    private int team;

    @ApiModelProperty(name = "teamId", value = "队伍id")
    private String teamId;

    @ApiModelProperty(name = "score", value = "分数", hidden = true)
    private Integer score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
