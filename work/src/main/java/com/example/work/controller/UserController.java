package com.example.work.controller;

import com.example.work.common.Const;
import com.example.work.common.ServerResponse;
import com.example.work.pojo.User;
import com.example.work.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class UserController {
    @Resource
    private UserService userService;

    //登陆
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = userService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(30*60);//session超时时间(秒)
        }
        return response;
    }

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ServerResponse<String> register(User user) {
        //System.out.println("有没有数据传过来????"+user.getUsername());
        return userService.register(user);
    }

    //检查邮箱与用户名是否已存在
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ServerResponse<String> check(String username, String email) {

        return userService.check(username, email);
    }

    //判断是否登陆
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public ServerResponse<User> getUserInfo(HttpSession session) {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登陆,无法获取信息");
    }

    //忘记密码
    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public ServerResponse<List<User>> forget(User user) {
        //System.out.println("asdasdas"+user.getEmail());
        return userService.forget(user);
    }

    //登出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ServerResponse<String> logout(HttpSession session) {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            session.removeAttribute(Const.CURRENT_USER);
            return ServerResponse.createBySuccessMessage("注销成功");
        }
        return ServerResponse.createByErrorMessage("注销失败，未登陆");
    }

}