package com.springboot.blog.service;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.model.Post;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO postGetByUniqueId(long uniqueId);

    PostDTO postGetByTitle(String title);
}
