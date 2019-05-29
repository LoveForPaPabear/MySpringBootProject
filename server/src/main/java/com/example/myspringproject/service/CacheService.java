package com.example.myspringproject.service;

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
     * @Date 14:37 2019/5/29
     * @Param [key]
     * @return java.util.List<java.lang.String>
     **/
    List<String> queryData(String key);

}
