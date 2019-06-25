package com.example.myspringproject.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 自定义缓存管理器
 * @Date 19:47 2019/6/5
 * @Param
 * @return
 **/
public class CustomCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        Cache cache = caches.get(s);
        if (cache == null) {
            cache = new RedisCache();
            caches.put(s, cache);
        }

        return cache;
    }
}
