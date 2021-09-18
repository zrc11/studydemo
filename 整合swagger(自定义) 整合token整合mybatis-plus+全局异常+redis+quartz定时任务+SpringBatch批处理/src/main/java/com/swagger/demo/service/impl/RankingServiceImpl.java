package com.swagger.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swagger.demo.mapper.CodeMapper;
import com.swagger.demo.mapper.RankingMapper;
import com.swagger.demo.model.entity.Code;
import com.swagger.demo.model.entity.Ranking;
import com.swagger.demo.service.CodeService;
import com.swagger.demo.service.RankingService;
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
public class RankingServiceImpl implements RankingService {

    /**
     * 静态变量：系统日志
     */
    private static final Log logger = LogFactory.getLog(RankingServiceImpl.class);

    @Resource
    private RankingMapper rankingMapper;

    @Override
    public List<Ranking> getRankingList() {
        QueryWrapper<Ranking> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("rankingScore");
        return rankingMapper.selectList(queryWrapper);
    }
}
