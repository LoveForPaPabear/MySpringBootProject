package com.example.myspringproject.controller;

import com.example.myspringproject.model.User;
import com.example.myspringproject.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/login")
    public void login(User user, HttpServletRequest request, HttpServletResponse response) {
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getUsername()), "用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getPassword()), "密码不能为空");

        System.out.println(request.getSession().getId());

        String userName = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        SecurityUtils.getSubject().login(usernamePasswordToken);

        Cookie cookie = new Cookie("dev", "1");
        cookie.setDomain("domain");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @GetMapping("/loginOut")
    public void loginOut() {
        SecurityUtils.getSubject().logout();
    }

}
