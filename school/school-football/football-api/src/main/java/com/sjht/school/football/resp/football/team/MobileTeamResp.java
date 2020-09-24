package com.sjht.school.football.resp.football.team;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * ***************************************************
 *
 * @ClassName MobileTeamResp
 * @Description 描述
 * @Author 张弛
 * @Date 2019/11/1 13:47
 * @Version V1.0
 * ****************************************************
 **/
public class MobileTeamResp implements Serializable {
    @ApiModelProperty(name = "id", value = "主id")
    private String id;//主ID
    @ApiModelProperty(name = "studentId", value = "学生ID")
    private String studentId;//学生id
    @ApiModelProperty(name = "eventId", value = "赛事ID")
    private String eventId;//赛事ID
    private String studentName;//学生姓名
    private String age;
    private String sex;
    private String teamName;//球队名
    private String teamId;//球队ID
    @ApiModelProperty(name = "score", value = "当场赛事个人进球数")
    private Integer score;//当场赛事比分

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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
