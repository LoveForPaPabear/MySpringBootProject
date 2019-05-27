package com.example.mySpringProject.config.test;

import com.example.mySpringProject.config.utils.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/redis")
/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description redis 测试controller
 * @Date 14:31 2019/5/27
 * @Param
 * @return
 **/
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;

    @GetMapping("/add")
    public boolean addRedis() {
        return redisService.expireKey("name", 20, TimeUnit.SECONDS);
    }


}
