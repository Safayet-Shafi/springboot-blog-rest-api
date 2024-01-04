package com.springboot.blog.dto;

import com.springboot.blog.model.Post;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CommentDTO {

    private long id;
    private String name;
    private String email;
    private String body;
    private Post post;
}
