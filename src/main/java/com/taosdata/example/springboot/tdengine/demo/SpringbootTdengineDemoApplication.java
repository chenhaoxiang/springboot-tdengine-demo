package com.taosdata.example.springboot.tdengine.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.taosdata.example.springboot.tdengine.demo.dao"})
@SpringBootApplication
public class SpringbootTdengineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTdengineDemoApplication.class, args);
    }
}
