package com.baizhi.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("echarts")
public class EchartsController {

    @RequestMapping("queryByUser")
    public HashMap<String,Object> queryByUser(){

        HashMap<String, Object> map = new HashMap<>();

        map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
        // 每月注册的用户数量，以后可以用groupby month子句查出，然后Echarts就可以动态展示了，下面的写死了
        map.put("boys",Arrays.asList(100, 200, 360, 100, 900, 200));
        map.put("girls",Arrays.asList(100, 200, 360, 100, 900, 200));

        return map;

    }
}
