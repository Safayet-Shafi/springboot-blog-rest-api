package com.springboot.blog.dto;

import com.springboot.blog.model.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {

    private long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;

}
