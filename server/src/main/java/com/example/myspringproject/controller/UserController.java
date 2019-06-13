package com.example.myspringproject.controller;

import com.example.myspringproject.model.User;
import com.example.myspringproject.service.UserService;
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
    public List<User> getValue(User user) {
        return userService.getUserList(user);
    }

    @PostMapping("/user")
    public int addValue(@RequestBody User user) {
        return userService.addUserList(Lists.newArrayList(user));
    }
}
