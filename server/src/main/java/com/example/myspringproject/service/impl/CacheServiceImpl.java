package com.example.myspringproject.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.example.myspringproject.service.CacheService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description
 * @Date 14:35 2019/5/29
 * @Param
 * @return
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CacheServiceImpl implements CacheService {
    @CreateCache(name = "myServiceCache", expire = 60)
    private Cache<String, String> cache;

    @Override
    public void createCacheDemo() {
        cache.put("myKey", "myValue");
        String myValue = cache.get("myKey");
        System.out.println("get 'myKey' from cache:" + myValue);
    }

    @Override
    public List<String> queryData(String key) {
        System.out.println(key + "进来了");
        return Lists.newArrayList("1");
    }
}
