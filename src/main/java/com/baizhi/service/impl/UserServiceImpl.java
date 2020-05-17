package com.baizhi.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void export() {
        List<User> users = userDao.selectAll();
        System.out.println("=============="+users);
        for (User user : users) {
            user.setPicImg("src/main/webapp/upload/photo/"+user.getPicImg());
        }

        //导出配置的参数   参数:标题名,工作薄名字
       // ExportParams exportParams = new ExportParams("用户信息", "用户信息表1");

        //参数：导出的配置,导出数据对应的实体类,导出的数据
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机班","学生"),User.class, users);

        try {
            workbook.write(new FileOutputStream(new File("E:/poi导出用户/user0201.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();

        //总条数  records
        UserExample example = new UserExample();
        Integer records = userDao.selectCountByExample(example);

        map.put("records",records);

        //总页数  total
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("total",total);

        //当前页  page
        map.put("page",page);

        //数据  rows
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<User> users = userDao.selectByRowBounds(new User(), rowBounds);
        map.put("rows",users);

        return map;
    }

    @Override
    public void update(User user) {

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());
        userDao.updateByExampleSelective(user,example);
    }
}
