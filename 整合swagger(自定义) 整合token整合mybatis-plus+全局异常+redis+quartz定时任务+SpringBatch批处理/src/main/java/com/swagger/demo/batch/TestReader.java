package com.swagger.demo.batch;

import com.swagger.demo.model.entity.Score;
import com.swagger.demo.service.ScoreService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @ClassName TestReader
 * @Description TODO
 * @Author zrc
 * @Date 14:24
 * @Version 1.0
 **/
@Component
public class TestReader implements ItemReader<Set<Score>> {

    @Resource
    private ScoreService scoreService;

    @Override
    public Set<Score> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        //数据读取器，可以自定义数据源，可以是文件，也可以是数据库。
        //将数据读取出来并返回到当前的batch中。
        return null;
    }

}
