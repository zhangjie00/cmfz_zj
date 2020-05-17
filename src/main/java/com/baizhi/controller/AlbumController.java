package com.baizhi.controller;

import com.baizhi.annotcation.AddCache;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.EditorKit;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("album")
@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page, Integer rows){
        return albumService.queryAll(page,rows);
    }


}
