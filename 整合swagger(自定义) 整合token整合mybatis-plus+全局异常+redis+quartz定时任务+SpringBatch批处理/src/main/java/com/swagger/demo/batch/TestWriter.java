package com.swagger.demo.batch;

import com.swagger.demo.model.entity.Score;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @ClassName TestWriter
 * @Description TODO
 * @Author zrc
 * @Date 14:26
 * @Version 1.0
 **/
@Component
public class TestWriter implements ItemWriter<Set<Score>> {

    @Override
    public void write(List<? extends Set<Score>> list) throws Exception {

    }
}
