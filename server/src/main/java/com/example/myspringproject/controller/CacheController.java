package com.example.myspringproject.controller;

import com.example.myspringproject.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 缓存实现controller
 * @Date 14:25 2019/5/29
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/getKey/{key}")
    public void getValue(@PathVariable String key) {
        cacheService.queryData(key);
        cacheService.createCacheDemo();
    }
}
