package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    HashMap<String,Object> queryByPage(Integer page, Integer rows);
    void export();
    void update(User user);

}
