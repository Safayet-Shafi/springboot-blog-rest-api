package com.springboot.blog.service;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.dto.ResponseModelDTO;
import com.springboot.blog.model.Post;

import java.util.List;

public interface PostService {

    ResponseModelDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO postGetByUniqueId(long uniqueId);

    PostDTO postGetByTitle(String title);

    PostDTO updateTypeConversion(PostDTO postDTO ,long uniqueId);
}
