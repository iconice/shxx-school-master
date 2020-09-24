package com.sjht.school.football.resp.football.statistical;

import java.io.Serializable;

/**
 * ***************************************************
 *
 * @ClassName GetSeasonLeaderboardListResp
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/25 15:51
 * @Version V1.0
 * ****************************************************
 **/
public class GetSeasonLeaderboardListResp implements Serializable {

    private String studentId;

    private String studentName;

    private String teamName;

    private Integer batchNo;

    private Integer status;

    private Integer score;

    private String teamId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
