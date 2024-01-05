package com.springboot.blog.service.serviceImpl;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.dto.ResponseModelDTO;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Post;
import com.springboot.blog.repository.PostRepo;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceImpl implements PostService {

       private final PostRepo postRepo;

      private final ModelMapper mapper;

       public ServiceImpl(PostRepo postRepo, ModelMapper mapper) {
           this.postRepo = postRepo;
           this.mapper = mapper;
       }

    @Override
    public ResponseModelDTO createPost(PostDTO postDTO) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        Post post = maptoEntity(postDTO);
        try{
            Post Post1 = postRepo.save(post);
            responseModelDTO.setResponseCode(1);
            responseModelDTO.setResponseMessage("SuccessFull");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return responseModelDTO;

    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(post -> maptoDTO(post)).toList();
    }

    @Override
    public PostDTO postGetByUniqueId(long uniqueId) {
        Post post = postRepo.findById(uniqueId).orElseThrow(() -> new ResourceNotFoundException("Post", "uniqueId", uniqueId));
        PostDTO postDTO = maptoDTO(post);


        return postDTO;
    }

    @Override
    public PostDTO postGetByTitle(String title) {
        Post post = postRepo.findByTitle(title);
        PostDTO postDTO = maptoDTO(post);
        return postDTO;
    }

    @Override
    public PostDTO updateTypeConversion(PostDTO postDTO, long uniqueId) {
        Post post = postRepo.findById(uniqueId).orElseThrow(() -> new ResourceNotFoundException("Post", "uniqueId", uniqueId));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post Post1 = postRepo.save(post); // after saving db returns entity have to convert this entity into dto
        return maptoDTO(Post1);

        
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
