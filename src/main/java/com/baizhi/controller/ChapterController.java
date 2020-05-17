package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows, String albumId) {

        HashMap<
                String, Object> map = (HashMap<String, Object>) chapterService.queryByPage(page, rows, albumId);

        return map;
    }

    @RequestMapping("edit")
    public String edit(Chapter chapter, String oper, String albumId) {

        String id=null;
        if(oper.equals("add")){

            chapter.setAlbumId(albumId);
            id = chapterService.add(chapter);
        }

        if(oper.equals("edit")){

        }

        if(oper.equals("del")){

        }

        return id;
    }

    @RequestMapping("uploadChapter")
    public void uploadChapter(MultipartFile src, String id, HttpServletRequest request){

        chapterService.uploadChapter(src,id,request);

    }

    @RequestMapping("audioDownload")
    public void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response){
        chapterService.audioDownload(audioName,request,response);
    }
}