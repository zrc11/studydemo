package com.swagger.demo.service;


import com.swagger.demo.model.entity.Code;
import com.swagger.demo.model.entity.Ranking;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrc
 * @since 2021-08-30
 */
public interface RankingService {

    /**
     * 获取排行数据
     * @return
     */
    List<Ranking> getRankingList();

}
