package com.zhancheng.backstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author BianShuHeng
 */
@ComponentScan(value = "com.zhancheng")
@SpringBootApplication
public class BackstageServiceApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BackstageServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackstageServiceApplication.class);
    }
}
