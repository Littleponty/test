package com.example.work.dao;

import com.example.work.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> forget(String email);

    int checkUsername(String username);

    int checkEmail(String email);

    User Login(@Param("username") String username, @Param("password") String password);
}