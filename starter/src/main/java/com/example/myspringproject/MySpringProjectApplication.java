package com.example.myspringproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 运行 主类
 * @Date 14:42 2019/5/22
 * @Param
 * @return
 **/
@Slf4j
@SpringBootApplication(scanBasePackages = "com.example.*")
@EnableCaching
@PropertySource("classpath:application.properties")
public class MySpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringProjectApplication.class, args);
    }

}
