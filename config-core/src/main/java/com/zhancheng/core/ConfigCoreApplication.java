package com.zhancheng.core;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author 边书恒
 * @Title: ConfigCoreApplication
 * @ProjectName zc-program
 * @Description: TODO
 * @date 2019/8/28 19:43
 */
@SpringBootApplication
@MapperScan("com.zhancheng.core.*")
public class ConfigCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCoreApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize(DataSize.ofBytes(10485760L));

        return factory.createMultipartConfig();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
