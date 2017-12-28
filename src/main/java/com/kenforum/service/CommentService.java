package com.kenforum.service;

import com.kenforum.entity.Comment;
import com.kenforum.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(final Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(final Long id) {
        commentRepository.delete(id);
    }

}
