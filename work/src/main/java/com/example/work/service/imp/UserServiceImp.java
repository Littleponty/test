package com.example.work.service.imp;

import javax.annotation.Resource;

import com.example.work.dao.UserMapper;
import com.example.work.pojo.User;
import com.example.work.service.UserService;
import com.example.work.common.ServerResponse;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


@Service("userService")
public class UserServiceImp implements UserService{
	@Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse<User>login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        User user = userMapper.Login(username,password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);
    }

    @Override
    public ServerResponse<String> register (User user) {
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("数据添加失败");
        } else {
            return ServerResponse.createBySuccessMessage("数据添加成功");
        }
    }

    @Override
    public ServerResponse<String> check(String username, String email) {

        int resultCount1= userMapper.checkUsername(username);
        if (resultCount1 > 0) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        int resultCount2 = userMapper.checkEmail(email);
        if (resultCount2 > 0) {
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }

        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<List<User>> forget(User user) {
        if(userMapper.forget(user.getEmail()) == null){
            return ServerResponse.createByErrorMessage("邮箱不存在！");
        }
        return ServerResponse.createBySuccess(userMapper.forget(user.getEmail()));
    }

}
