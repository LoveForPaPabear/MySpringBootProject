package com.example.myspringproject.mapper;

import com.example.myspringproject.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int addUserList(List<User> userList);

    int updateUserList(List<User> userList);

    List<User> getUserList(User user);

    int deleteUser(User user);
}
