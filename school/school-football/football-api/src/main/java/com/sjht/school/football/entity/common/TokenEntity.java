package com.sjht.school.football.entity.common;

import com.baomidou.mybatisplus.annotations.TableName;
import com.sjht.school.common.entity.BaseEntity;

import java.util.Date;

/**
 * ***************************************************
 * @ClassName TokenEntity
 * @Description token实体类
 * @Author maojianyun
 * @Date 2019/10/28 9:59
 * @Version V1.0
 * ****************************************************
 **/
@TableName("base_token")
public class TokenEntity extends BaseEntity {

    private String userId;

    private String token;

    private Date expirationTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }
}
