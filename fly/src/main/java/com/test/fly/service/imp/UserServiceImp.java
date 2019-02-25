package com.test.fly.service.imp;

import javax.annotation.Resource;

import com.test.fly.dao.DataMapper;
import com.test.fly.pojo.Data;
import org.springframework.stereotype.Service;

import com.test.fly.service.UserService;
import com.test.fly.common.ServerResponse;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService{
	@Resource
    private DataMapper dataMapper;

    @Override
    public ServerResponse<List<Data>> Locate() {
        long nowTime = new Date().getTime();
        if (dataMapper.selectLocate() == null) {
            return ServerResponse.createByErrorMessage("定位错误！");
        }
        for (int i=dataMapper.selectLocate().size()-1;i>=0;i--) {
            //最近时间（毫秒）
            Data log = dataMapper.selectLocate().get(i);
            if (nowTime - log.getRq().getTime() > 60000) {
                dataMapper.selectLocate().remove(log);
            }
        }
        return ServerResponse.createBySuccess(dataMapper.selectLocate());
    }

    @Override
    public ServerResponse<List<Data>> History() {
        if(dataMapper.historyLocate() ==null){
            return ServerResponse.createBySuccessMessage("数据库返回历史定位错误！");
        }
        return ServerResponse.createBySuccess(dataMapper.historyLocate());
    }

    @Override
    public ServerResponse<String> Adds(Data data) {
        int resultCount = dataMapper.insert(data);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("数据添加失败");
        } else {
            return ServerResponse.createBySuccessMessage("数据添加成功");
        }
    }

}
