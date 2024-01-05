package com.springboot.blog.dto;

import com.springboot.blog.model.Post;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private long comment_id;
    private String name;
    private String email;
    private String body;
    private Post post;
}
