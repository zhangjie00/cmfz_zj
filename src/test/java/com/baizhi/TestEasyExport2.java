package com.baizhi;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEasyExport2 {
    public static void main(String[] args) throws Exception {
        List<User> list = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            User user = new User();
            user.setId("r3433tt3t");
            user.setName("kimi");
            user.setBir(new Date( ));
            list.add(user);
        }

        //参数：标题，表名，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("哈哈哈","酒鬼先生"),User.class, list);
        workbook.write(new FileOutputStream(new File("E:/poi导出用户/user03.xls")));
        workbook.close();

    }
}
