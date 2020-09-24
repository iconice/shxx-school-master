package com.sjht.school.football.service.impl.common;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sjht.school.football.dao.common.TokenDao;
import com.sjht.school.football.entity.common.TokenEntity;
import com.sjht.school.football.service.common.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ***************************************************
 * @ClassName TokenServiceImpl
 * @Description 描述
 * @Author maojianyun
 * @Date 2019/10/28 10:02
 * @Version V1.0
 * ****************************************************
 **/
@Service
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements IService<TokenEntity>, TokenService {

    @Override
    public boolean insetToken(TokenEntity entity) {
        return this.insert(entity);
    }

    @Override
    public boolean deleteTokenByuserId(String userId) {
        TokenEntity entity = new TokenEntity();
        Wrapper<TokenEntity> wrapper = new EntityWrapper<>();
        wrapper.where("user_id={0} AND deletec=0", userId);
        entity.setDeletec(1);
        entity.setUpdateTime(new Date());
        return this.update(entity, wrapper);
    }

    @Override
    public boolean deleteTokenByToken(String token) {
        TokenEntity entity = new TokenEntity();
        Wrapper<TokenEntity> wrapper = new EntityWrapper<>();
        wrapper.where("deletec=0", token);
        entity.setDeletec(1);
        entity.setUpdateTime(new Date());
        return this.update(entity, wrapper);
    }
}
