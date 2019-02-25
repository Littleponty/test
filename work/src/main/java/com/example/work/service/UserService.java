package com.example.work.service;

import com.example.work.common.ServerResponse;
import com.example.work.pojo.User;

import java.util.List;


public interface UserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> check(String username, String email);

    ServerResponse<List<User>> forget(User user);
}
