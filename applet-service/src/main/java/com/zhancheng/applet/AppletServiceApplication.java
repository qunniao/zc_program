package com.zhancheng.applet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.zhancheng")
@SpringBootApplication
public class AppletServiceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppletServiceApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(AppletServiceApplication.class);
    }
}
