package com.springboot.blog.service;

import com.springboot.blog.dto.PostDTO;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);
}
