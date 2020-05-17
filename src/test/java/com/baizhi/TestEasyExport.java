package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEasyExport {

    public static void main(String[] args) throws Exception {
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            User user = new User();
            user.setId(i+"");
            user.setName("张三"+i);
            user.setBir(new Date());
//            user.setPhoto("E:/后期项目/day7/ldh.jpg");
            list.add(user);
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),User.class, list);
        workbook.write(new FileOutputStream(new File("E:/poi导出用户/user1.xls")));


    }

}
