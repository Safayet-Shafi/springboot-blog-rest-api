package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getCommentsByPostId(long postId);
}
