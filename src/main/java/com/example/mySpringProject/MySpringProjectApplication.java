package com.example.mySpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//默认加载当前文件目录下的文件
public class MySpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringProjectApplication.class, args);
    }

}
