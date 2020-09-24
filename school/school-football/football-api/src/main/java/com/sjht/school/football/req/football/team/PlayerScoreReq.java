package com.sjht.school.football.req.football.team;

/**
 * ***************************************************
 * @ClassName PlayerScoreReq
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/24 17:16
 * @Version V1.0
 * ****************************************************
 **/
public class PlayerScoreReq {

    private String teamId;

    private String studentId;

    private Integer score = 0;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
