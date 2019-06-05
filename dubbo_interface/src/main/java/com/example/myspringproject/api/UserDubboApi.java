package com.example.myspringproject.api;

import java.util.List;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description dubbo service
 * @Date 10:39 2019/6/4
 * @Param
 * @return
 **/
public interface UserDubboApi {
    /*
     * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
     * @Description 获取用户id
     * @Date 11:06 2019/6/4
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    List<String> getUserIdList();
}
