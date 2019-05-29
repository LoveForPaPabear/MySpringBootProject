package com.example.myspringproject.controller;

import com.example.myspringproject.utils.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description redis 测试controller
 * @Date 14:31 2019/5/27
 * @Param
 * @return
 **/
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/add")
    public void addRedis() {
        redisService.addKey("key", "keyName", 6000L);
    }

    @GetMapping("/get")
    public String getRedis() {
        return redisService.getKey("key");
    }
}
