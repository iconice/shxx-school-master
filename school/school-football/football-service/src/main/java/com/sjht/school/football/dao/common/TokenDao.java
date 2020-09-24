package com.sjht.school.football.dao.common;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjht.school.football.entity.common.TokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * ***************************************************
 *
 * @ClassName TokenDao
 * @Description token
 * @Author maojianyun
 * @Date 2019/10/28 10:02
 * @Version V1.0
 * ****************************************************
 **/
@Mapper
@Component
public interface TokenDao extends BaseMapper<TokenEntity> {
}
