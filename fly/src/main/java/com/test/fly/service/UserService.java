package com.test.fly.service;


import com.test.fly.common.ServerResponse;
import com.test.fly.pojo.Data;

import java.util.List;

public interface UserService {

    ServerResponse<String> Adds(Data data);

    ServerResponse<List<Data>> Locate();
    ServerResponse<List<Data>> History();
}
