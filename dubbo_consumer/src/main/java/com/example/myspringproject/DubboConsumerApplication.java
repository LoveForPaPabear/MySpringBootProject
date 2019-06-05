package com.example.myspringproject;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 运行 主类
 * @Date 14:42 2019/5/22
 * @Param
 * @return
 **/
@Slf4j
@SpringBootApplication
@EnableDubboConfiguration
public class DubboConsumerApplication {
    @Reference(url = "dubbo://127.0.0.1:20880")
    private com.example.myspringproject.api.UserDubboApi UserDubboApi;


    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
    @Bean
    public ApplicationRunner runner()  {
        return args -> System.out.println(UserDubboApi.getUserIdList());
    }
}
