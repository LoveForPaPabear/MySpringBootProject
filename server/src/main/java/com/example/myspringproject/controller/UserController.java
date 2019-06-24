package com.example.myspringproject.controller;

import com.example.myspringproject.model.User;
import com.example.myspringproject.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public PageInfo getValue(User user) {
        List<User> userList = userService.getUserList(user);
        return new PageInfo<>(userList);
    }

    @PostMapping("/user")
    public int addValue(@RequestBody User user) {
        return userService.addUserList(Lists.newArrayList(user));
    }
}
