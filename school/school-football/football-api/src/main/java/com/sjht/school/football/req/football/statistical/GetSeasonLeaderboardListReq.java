package com.sjht.school.football.req.football.statistical;

import com.sjht.school.common.entity.BaseEntity;
import com.sjht.school.common.entity.PageBase;

import java.io.Serializable;

/**
 * ***************************************************
 * @ClassName GetSeasonLeaderboardListResp
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/25 15:51
 * @Version V1.0
 * ****************************************************
 **/
public class GetSeasonLeaderboardListReq extends PageBase {

    private String gradeId;

    private String classId;

    private String seasonId;

    private String category;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
