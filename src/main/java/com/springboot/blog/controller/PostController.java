package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.dto.ResponseModelDTO;
import com.springboot.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseModelDTO postInsert(@RequestBody PostDTO postDTO) {
        System.out.println("postDTO = " + postDTO);
        return postService.createPost(postDTO);
    }

    @GetMapping()
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }
    @GetMapping("/{uniqueId}")
    public PostDTO getPostByUniqueId(@PathVariable long uniqueId) {
        return postService.postGetByUniqueId(uniqueId);
    }

    @GetMapping("/title/{title}")
    public Map<String, Object> getPostByTitle(@PathVariable String title) {

        Map<String, Object> map = new HashMap<>();
        map.put("Title", postService.postGetByTitle(title));
        return map;
    }
    @GetMapping("/title1/{title}")
    public PostDTO getPostByTitle1(@PathVariable String title) {
        return postService.postGetByTitle(title);
    }

    @PatchMapping("/{uniqueId}")
    public PostDTO updatePost(@PathVariable (name = "uniqueId") long uniqueId,
                                                       @RequestBody PostDTO postDTO){
        PostDTO postDTO1=postService.updateTypeConversion(postDTO,uniqueId);
        return postDTO1;
    }

}
