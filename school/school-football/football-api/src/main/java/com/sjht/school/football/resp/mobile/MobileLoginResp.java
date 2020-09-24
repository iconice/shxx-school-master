package com.sjht.school.football.resp.mobile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 * @ClassName MobileLoginResp
 * @Description 登录相应实体
 * @Author maojianyun
 * @Date 2019/10/28 9:50
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "MobileLoginResp", description = "移动端登录响应实体")
public class MobileLoginResp implements Serializable {

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

}
