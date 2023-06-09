package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDTO;
import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/comments/{postId}")
   public List<CommentDTO> getCommentByPostId(@PathVariable(value = "postId") Long postId){
     return commentService.getCommentsByPostId(postId);
    }

    @PostMapping("/posts/comments/{postId}")
    public CommentDTO createComment(@PathVariable(value = "postId") long postId,
                                    @RequestBody CommentDTO commentDTO){
        return commentService.createComment(postId,commentDTO);
    }

}
