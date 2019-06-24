package com.example.myspringproject.service.impl;

import com.example.myspringproject.mapper.UserMapper;
import com.example.myspringproject.model.User;
import com.example.myspringproject.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(timeout = 2)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUserList(List<User> userList) {
        return userMapper.addUserList(userList);
    }

    @Override
    public int updateUserList(List<User> userList) {
        return userMapper.updateUserList(userList);
    }

    @Override
    public List<User> getUserList(User user) {
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        return userMapper.getUserList(user);
    }

    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }
}
