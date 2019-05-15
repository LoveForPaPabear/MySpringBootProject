package com.example.mySpringProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 读取redis配置文件
 * @Date 16:39 2019/5/7
 * @Param
 * @return
 **/
@Configuration
public class RedisConfigure {

    @Value("${spring.redis.database}")
    private String dataBase;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private String timeout;
}
