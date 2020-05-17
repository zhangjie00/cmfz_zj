package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;
    @Override
    public void login(Admin admin, String inputCode, HttpServletRequest request) {
        HttpSession session =request.getSession();
        String code1 = (String)session.getAttribute("code");
        if(code1.equalsIgnoreCase(inputCode)){
            Admin admin1 = adminDao.selectOne(admin);
            if (admin1 != null){
                session.setAttribute("admin",admin1);
            }else{
                System.out.println("用户名或密码错误！");
            }
        }else{
            System.out.println("验证码错误！");
        }

    }
}
