package com.qxf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: qiuxinfa
 * @Date: 2020/5/5
 * @Description: com.qxf
 */
@SpringBootApplication
@MapperScan("com.qxf.dao")
public class BootVueOnlineExam {
    public static void main(String[] args) {
        SpringApplication.run(BootVueOnlineExam.class,args);
    }
}
