package com.example.myspringproject.service.impl;

import com.example.myspringproject.service.CacheService;
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

    @Override
    public List<String> queryData(String key) {
        return null;
    }
}
