package com.sjht.school.football.resp.football.statistical;

import java.io.Serializable;

/**
 * ***************************************************
 *
 * @ClassName GetSeasonScoreboardList
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/27 19:32
 * @Version V1.0
 * ****************************************************
 **/
public class GetSeasonScoreboardList implements Serializable {

    private String teamId;

    private String teamName;

    private Integer batchNo = 0;

    private Integer score = 0;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
