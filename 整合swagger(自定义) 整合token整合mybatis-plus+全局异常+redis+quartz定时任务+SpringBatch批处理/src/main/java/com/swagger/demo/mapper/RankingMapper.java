package com.swagger.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swagger.demo.model.entity.Code;
import com.swagger.demo.model.entity.Ranking;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-08-30
 */
@Mapper
public interface RankingMapper extends BaseMapper<Ranking> {

}
