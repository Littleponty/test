package com.example.work.service.imp;

import com.example.work.common.ServerResponse;
import com.example.work.dao.BlogMapper;
import com.example.work.pojo.Blog;
import com.example.work.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImp implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public ServerResponse<String> Adds(Blog blog) {
        int resultCount = blogMapper.insert(blog);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("数据添加失败");
        } else {
            return ServerResponse.createBySuccessMessage("数据添加成功");
        }
    }

    @Override
    public ServerResponse<List<Blog>> Query(Blog blog) {
        if(blogMapper.query() == null){
            return ServerResponse.createByErrorMessage("数据库返回数据错误！");
        }
        return ServerResponse.createBySuccess(blogMapper.query());
    }
}
