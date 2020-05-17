package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Excel(name = "Id")
    private Integer id;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "盐值")
    private String salt;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "头像",type=2,width = 30,height = 20)
    private String picImg;
    @Excel(name = "名字")
    private String name;
    @Excel(name = "法名")
    private String legalName;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "城市")
    private String city;

    @Excel(name = "签名")
    private String sign;

    @Excel(name = "注册时间",format="yyyy年MM月dd日",width = 20,height = 10)
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date regTime;

    @Excel(name="上师id")
    private Integer guruId;


}