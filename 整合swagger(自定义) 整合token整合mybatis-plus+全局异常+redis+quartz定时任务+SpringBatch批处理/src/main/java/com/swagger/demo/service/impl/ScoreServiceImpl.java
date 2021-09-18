package com.swagger.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swagger.demo.mapper.RankingMapper;
import com.swagger.demo.mapper.ScoreMapper;
import com.swagger.demo.model.entity.Ranking;
import com.swagger.demo.model.entity.Score;
import com.swagger.demo.service.RankingService;
import com.swagger.demo.service.ScoreService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zrc
 * @since 2021-08-30
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    /**
     * 静态变量：系统日志
     */
    private static final Log logger = LogFactory.getLog(ScoreServiceImpl.class);

    @Resource
    private ScoreMapper scoreMapper;


    @Override
    public int updateRankingDate() {
        //获取分数表的所有人的数据
//        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
//        List<Score> list = scoreMapper.selectList(queryWrapper);
//
        return 1;
    }

    @Override
    public List<Score> getScoreList() {
        //获取分数表的所有人的数据
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        List<Score> list = scoreMapper.selectList(queryWrapper);
        return list;
    }
}
