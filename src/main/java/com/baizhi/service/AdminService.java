package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    public void login(Admin admin, String inputCode, HttpServletRequest request);
}
