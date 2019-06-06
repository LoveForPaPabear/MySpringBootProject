package com.example.myspringproject.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 自定义缓存管理器
 * @Date 19:47 2019/6/5
 * @Param 
 * @return 
 **/
public class CustomCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<K,V>();
    }
}
