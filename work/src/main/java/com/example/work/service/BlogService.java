package com.example.work.service;

import com.example.work.common.ServerResponse;
import com.example.work.pojo.Blog;

import java.util.List;

public interface BlogService {

    ServerResponse<String> Adds(Blog blog);

    ServerResponse<List<Blog>>Query(Blog blog);
}
