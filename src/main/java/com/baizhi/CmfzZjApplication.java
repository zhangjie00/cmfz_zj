package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.PostConstruct;
@MapperScan("com.baizhi.dao")
@SpringBootApplication

public class CmfzZjApplication {
    public static void main(String[] args) {
        //作es功能特意加上的,否则程序运行报错
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(CmfzZjApplication.class, args);
    }

}
