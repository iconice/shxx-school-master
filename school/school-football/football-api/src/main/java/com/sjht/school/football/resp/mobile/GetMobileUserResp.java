package com.sjht.school.football.resp.mobile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * ***************************************************
 *
 * @ClassName GetMobileUserResp
 * @Description 描述
 * @Author 张弛
 * @Date 2019/10/29 10:31
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "GetMobileUserResp", description = "移动端用户信息响应实体")
public class GetMobileUserResp implements Serializable {
    @ApiModelProperty(name = "userId", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "email", value = "邮箱号")
    private String email;

    @ApiModelProperty(name = "tell", value = "电话号码")
    private String tell;

    @ApiModelProperty(name = "userType", value = "用户类型：1-系统用户 2-老师用户")
    private Integer userType;

    @ApiModelProperty(name = "name", value = "真实姓名")
    private String name;

    @ApiModelProperty(name = "status", value = "用户状态")
    private  String status;//用户状态

    @ApiModelProperty(name = "del", value = "token状态")
    private  String del;//token状态

    private Date expirationTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
