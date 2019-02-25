package com.test.fly.controller;

import com.test.fly.common.ServerResponse;
import com.test.fly.pojo.Data;
import com.test.fly.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/locate", method = RequestMethod.POST)
    public ServerResponse<List<Data>> getLocate() {
        return userService.Locate();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse<String> Add(Data data) {

        return userService.Adds(data);
    }

    @ResponseBody
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public ServerResponse<List<Data>> History() {

        return userService.History();
    }
}