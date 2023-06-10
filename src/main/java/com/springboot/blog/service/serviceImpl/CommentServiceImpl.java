package com.springboot.blog.service.serviceImpl;
import com.springboot.blog.dto.CommentDTO;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Comment;
import com.springboot.blog.model.Post;
import com.springboot.blog.repository.CommentRepo;
import com.springboot.blog.repository.PostRepo;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.BadLocationException;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final PostRepo postRepo;

    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepo commentRepo,PostRepo postRepo, ModelMapper mapper) {
        this.commentRepo = commentRepo;
        this.mapper = mapper;
        this.postRepo = postRepo;
    }






    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        List<Comment> comments=commentRepo.findByPostId(postId);

        return comments.stream().map(comment -> maptoDTO(comment)).toList();
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment=maptoEntity(commentDTO);
        // retrive post entity by postID
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        comment.setPost(post); // set post to comment entity

        Comment newcomment=commentRepo.save(comment);
        return maptoDTO(newcomment);
    }

    @Override
    public CommentDTO getCommentByCommentAndPOstId(long postId, long commentId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

        if(!Objects.equals(comment.getPost().getId(), post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment Doesnot belongs to This post");
        }

        return maptoDTO(comment);

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
