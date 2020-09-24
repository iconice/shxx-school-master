package com.sjht.school.football.req.mobile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ***************************************************
 * @ClassName MobileLoginReq
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/28 9:33
 * @Version V1.0
 * ****************************************************
 **/
@ApiModel(value = "MobileLoginReq", description = "移动端登录请求实体")
public class MobileLoginReq implements Serializable {

    @ApiModelProperty(name = "userName", value = "登录用户名")
    private String userName;

    @ApiModelProperty(name = "password", value = "登录密码")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
