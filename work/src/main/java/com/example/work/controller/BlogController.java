package com.example.work.controller;

import com.example.work.common.ServerResponse;
import com.example.work.pojo.Blog;
import com.example.work.service.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BlogController {

    @Resource
    private BlogService blogService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse<String> Add(Blog blog) {

        return blogService.Adds(blog);
    }

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ServerResponse<List<Blog>>Query(Blog blog) {

        return blogService.Query(blog);
    }
}
