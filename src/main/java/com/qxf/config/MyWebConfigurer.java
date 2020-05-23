package com.qxf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyWebConfigurer
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/23 22:50
 **/
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置资源的虚拟路径映射，使得可以访问硬盘资源
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "D:/attachment/");
    }

}
