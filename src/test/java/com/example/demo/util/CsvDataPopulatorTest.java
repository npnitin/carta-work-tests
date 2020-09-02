package com.example.demo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
public class CsvDataPopulatorTest {

    @InjectMocks
    CsvDataPopulator csvDataPopulator;

    @Test
    public void getAvgOfColTest(){
        List<Object> list = new ArrayList<>();
        list.add(10l);
        list.add(20l);
        list.add(30l);
        Map<String,List<Object>> map = new HashMap<>();
        map.put("col",list);
        double result =csvDataPopulator.getAvgOfCol(map,"col");
        assertEquals(20,result,0);
    }

}