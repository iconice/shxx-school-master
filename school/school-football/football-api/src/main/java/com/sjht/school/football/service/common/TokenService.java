package com.sjht.school.football.service.common;

import com.sjht.school.football.entity.common.TokenEntity;

/**
 * ***************************************************
 *
 * @ClassName TokenService
 * @Description token接口
 * @Author maojianyun
 * @Date 2019/10/28 10:01
 * @Version V1.0
 * ****************************************************
 **/
public interface TokenService {

    /**
     * 生成token
     * @param entity
     * @return
     */
    boolean insetToken(TokenEntity entity);

    /**
     * 删除token
     * @param userId
     * @return
     */
    boolean deleteTokenByuserId(String userId);

    /**
     * 退出登录删除token
     * @param token
     * @return
     */
    boolean deleteTokenByToken(String token);
}
