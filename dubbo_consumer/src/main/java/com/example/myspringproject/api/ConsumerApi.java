package com.example.myspringproject.api;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description
 * @Date 17:19 2019/6/4
 * @Param
 * @return
 **/
@Component
public class ConsumerApi {

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:20880")
    private UserDubboApi UserDubboApi;

    public void getDubboApi() {
        System.out.println(UserDubboApi.getUserIdList());
    }
}
