package com.swagger.demo.batch;

import com.swagger.demo.model.entity.Product;
import com.swagger.demo.model.entity.Score;
import io.swagger.models.auth.In;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName TestProcessor
 * @Description TODO
 * @Author zrc
 * @Date 14:25
 * @Version 1.0
 **/
@Component
public class TestProcessor implements ItemProcessor<Object, Set<Score>> {

    @Override
    public Set<Score> process(Object o) throws Exception {
        List<Score> list = (List<Score>)o;
        //处理数据
        Set<Score> set = new TreeSet<>();
        for (Score score : list) {
            set.add(score);
            for (Score score1 : set) {
                if(score1.getUid().equals(score.getUid())){
                    Integer integer = Integer.parseInt(score1.getScore())+Integer.parseInt(score.getScore());
                    score1.setScore(integer.toString());
                }
            }
        }
        System.out.println("set:"+set);
        return set;
    }
}
