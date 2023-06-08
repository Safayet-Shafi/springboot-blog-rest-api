package com.springboot.blog.repository;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    Post findByTitle(@Param("title") String title);
}
