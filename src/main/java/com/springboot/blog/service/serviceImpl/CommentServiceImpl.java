package com.springboot.blog.service.serviceImpl;
import com.springboot.blog.dto.CommentDTO;
import com.springboot.blog.model.Comment;
import com.springboot.blog.repository.CommentRepo;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepo commentRepo, ModelMapper mapper) {
        this.commentRepo = commentRepo;
        this.mapper = mapper;
    }






    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        List<Comment> comments=commentRepo.findByPostId(postId);

        return comments.stream().map(comment -> maptoDTO(comment)).toList();
    }

    private CommentDTO maptoDTO(Comment comment) {
        CommentDTO commentDTO= mapper.map(comment,CommentDTO.class);
        return commentDTO;
    }

    private Comment maptoEntity(CommentDTO commentDTO) {
        Comment comment = mapper.map(commentDTO, Comment.class);
        return comment;
    }
}
