package com.example.myspringproject.service;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;

import java.util.List;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description
 * @Date 14:36 2019/5/29
 * @Param
 * @return
 **/
public interface CacheService {


    /**
     * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
     * @Description demo
     * @Date 20:11 2019/5/29
     * @Param []
     * @return void
     **/
    void createCacheDemo();
    
    /**
     * @return java.util.List<java.lang.String>
     * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
     * @Date 14:37 2019/5/29
     * 缓存的类型，包括CacheType.REMOTE、CacheType.LOCAL、CacheType.BOTH。
     * 如果定义为BOTH，会使用LOCAL和REMOTE组合成两级缓存
     * @Param [key]
     **/
    @Cached(name ="userCache",key = "#key", expire = 60, cacheType = CacheType.BOTH)
    @CacheRefresh(refresh = 60)
    List<String> queryData(String key);

}
