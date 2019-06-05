package com.example.myspringproject.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.myspringproject.api.UserDubboApi;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(interfaceClass = UserDubboApi.class, timeout = 10000)
@Component
public class UserDubboApiImpl implements UserDubboApi {


    @Override
    public List<String> getUserIdList() {
        return Lists.newArrayList("1");
    }
}
