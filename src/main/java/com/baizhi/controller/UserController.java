package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("queryByPage")
    public HashMap<String,Object> queryByPage(Integer page,Integer rows){

        HashMap<String, Object> map = userService.queryByPage(page, rows);

        return map;
    }

    @RequestMapping("export")
    public HashMap<String, String> export(){

        HashMap<String, String> map = new HashMap<>();

        try {
            userService.export();
            map.put("message","导出成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","导出失败");
        }

        return map;

    }

    @RequestMapping("edit")
    public String edit(User user, String oper){

        System.out.println("==controller: user== "+user);

        String id=null;

        //添加
        if(oper.equals("add")){
            //id= userService.add(banner);
        }
        //修改
        if(oper.equals("edit")){
            userService.update(user);
        }
        //删除
        if(oper.equals("del")){
            //userService.delete(banner);
        }
        return id;
    }

}
