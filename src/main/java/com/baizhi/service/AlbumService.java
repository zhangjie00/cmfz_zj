package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {


    Map<String,Object> queryAll(Integer page, Integer rows);

}
