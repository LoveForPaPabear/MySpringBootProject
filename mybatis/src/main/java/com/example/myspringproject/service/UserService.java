package com.example.myspringproject.service;

import com.example.myspringproject.model.User;

import java.util.List;

public interface UserService {

    int addUserList(List<User> userList);

    int updateUserList(List<User> userList);

    List<User> getUserList(User user);

    int deleteUser(User user);

    User findUserByName(User user);
}
