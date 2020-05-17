package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ArticleService {
    HashMap<String,Object> queryByPage(Integer page, Integer rows);

    void delete(Article article);

    void add(Article article);

    void update(Article article);

    List<Article> querySearchs(String content);

}
