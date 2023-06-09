package com.springboot.blog.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CommentDTO {

    private long id;
    private String name;
    private String email;
    private String body;
}
