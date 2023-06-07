package com.springboot.blog.service.serviceImpl;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.model.Post;
import com.springboot.blog.repository.PostRepo;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ServiceImpl implements PostService {

       private final PostRepo postRepo;

      private final ModelMapper mapper;

       public ServiceImpl(PostRepo postRepo, ModelMapper mapper) {
           this.postRepo = postRepo;
           this.mapper = mapper;
       }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = maptoEntity(postDTO); //given dto in converted in entity for saving
        Post Post1 = postRepo.save(post); // after saving db returns entity have to convert this entity into dto
        return maptoDTO(Post1); //returned entity converted into dto
    }

    private PostDTO maptoDTO(Post post) {
        PostDTO postDTO = mapper.map(post, PostDTO.class);
        return postDTO;
    }

    private Post maptoEntity(PostDTO postDTO) {
        Post post = mapper.map(postDTO, Post.class);
        return post;
    }
}
