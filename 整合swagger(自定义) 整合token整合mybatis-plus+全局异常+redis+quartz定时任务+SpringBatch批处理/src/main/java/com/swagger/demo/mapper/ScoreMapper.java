package com.swagger.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swagger.demo.model.entity.Score;
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
public interface ScoreMapper extends BaseMapper<Score> {

}
