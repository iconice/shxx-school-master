package com.sjht.school.football.req.football.event;

import com.sjht.school.common.entity.PageBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 *
 * @ClassName
 * @Description 描述
 * @Author 张弛
 * @Date 2019/10/30 11:48
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "MobileEventListReq", description = "移动端赛事信息请求实体")
public class MobileEventListReq extends PageBase implements Serializable{
    @ApiModelProperty(name = "gradeId", value = "届ID", required = true)
    private String gradeId;//届
    @ApiModelProperty(name = "category", value = "赛事栏目",required = true)
    private Integer category;//赛事栏目
    @ApiModelProperty(name = "seasonId", value = "赛季ID")
    private String seasonId;//赛季ID
    @ApiModelProperty(name = "status", value = "状态")
    private  Integer status;
    @ApiModelProperty(name = "classId", value = "班级ID")
    private String classId;//班级ID
    @ApiModelProperty(name = "batchNo", value = "赛事轮次")
    private String batchNo;//赛事轮次

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}
