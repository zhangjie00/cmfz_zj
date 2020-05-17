package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public interface ChapterService {
    Map<String,Object> queryByPage(Integer page, Integer rows, String albumId);

    String add(Chapter chapter);

    void update(Chapter chapter);

    void uploadChapter(MultipartFile src, String id, HttpServletRequest request);

    void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response);

}
