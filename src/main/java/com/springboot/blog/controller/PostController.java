package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public PostDTO postInsert(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

}
