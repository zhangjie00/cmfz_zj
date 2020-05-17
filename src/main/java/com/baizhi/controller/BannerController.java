package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Banner banner,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            if("add".equals(oper)){
               // System.out.println("pppppppppppppppp"+banner);
                String id = bannerService.add(banner);
                map.put("message",id);
            }
            if("edit".equals(oper)){
               bannerService.edit(banner);
            }
            if("del".equals(oper)){
                bannerService.del(banner.getId(), request);
            }
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile cover, String id, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
      //  System.out.println("................."+cover);
        //System.out.println("============="+cover.getOriginalFilename());
        try {
//            文件上传
            cover.transferTo(new File(request.getServletContext().getRealPath("/banner/img"),cover.getOriginalFilename()));
//            修改banner对象中cover属性
            Banner banner = new Banner();
            banner.setId(id);
            banner.setCover(cover.getOriginalFilename());
            //System.out.println("++++++++++++++++++++"+banner);
            bannerService.edit(banner);
            map.put("status",true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }


}
