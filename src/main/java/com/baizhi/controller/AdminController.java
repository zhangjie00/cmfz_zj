package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;


    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String securityCode = SecurityCode.getSecurityCode();
        System.out.println(securityCode);
        HttpSession session = request.getSession();
        session.setAttribute("code",securityCode);
//        往redis中存储验证码的标记 jedis.set("securityCode",securityCode);
        BufferedImage image = SecurityImage.createImage(securityCode);
        response.setContentType("image/png");
        ImageIO.write(image,"png",response.getOutputStream());
    }

    @RequestMapping("login")
    @ResponseBody
    //如果没用注解，可以把上面的@controller替换为@RestController,效果等同！
    public Map<String,Object> login(Admin admin,String inputCode,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try{
            adminService.login(admin,inputCode,request);
           // System.out.println("++++++++++"+admin);
            map.put("status",true);
        }catch(Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
}
