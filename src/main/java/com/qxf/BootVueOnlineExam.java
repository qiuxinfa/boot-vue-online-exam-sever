package com.qxf;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Auther: qiuxinfa
 * @Date: 2020/5/5
 * @Description: com.qxf
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.qxf.dao")
@EnableTransactionManagement
public class BootVueOnlineExam {
    public static void main(String[] args) {
        SpringApplication.run(BootVueOnlineExam.class,args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
