package com.sjht.school.football.req.mobile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 *
 * @ClassName GetBatchNoListReq
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/29 10:33
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "GetBatchNoListReq", description = "得到轮次请求实体")
public class GetBatchNoListReq implements Serializable {

    @ApiModelProperty(name = "gradeId", value = "年级id")
    private String gradeId;

    @ApiModelProperty(name = "classId", value = "班级id")
    private String classId;

    @ApiModelProperty(name = "category", value = "类型")
    private Integer category;

    @ApiModelProperty(name = "seasonId", value = "赛季id")
    private String seasonId;

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }
}
