package com.baizhi.service;

import com.baizhi.entity.Banner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BannerService {
    Map<String,Object> queryAll(Integer page, Integer rows);
    String add(Banner banner);
    void edit(Banner banner);
    void del(String id, HttpServletRequest request);
    List<Banner> selectAll();

}
