package com.my.course.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;

/**
 * Created by lin.pingbin on 2018/1/19.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class FileConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20MB");
        factory.setMaxRequestSize("20MB");
        return factory.createMultipartConfig();
    }
}
