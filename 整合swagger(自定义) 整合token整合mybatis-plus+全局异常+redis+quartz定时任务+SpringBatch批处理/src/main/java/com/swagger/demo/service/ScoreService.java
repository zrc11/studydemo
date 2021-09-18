package com.swagger.demo.service;


import com.swagger.demo.model.entity.Code;
import com.swagger.demo.model.entity.Ranking;
import com.swagger.demo.model.entity.Score;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrc
 * @since 2021-08-30
 */
public interface ScoreService {

    /**
     * 更新排名数据
     * @return
     */
    int updateRankingDate();

    /**
     * 查看分数列表
     * @return
     */
    List<Score> getScoreList();
}
