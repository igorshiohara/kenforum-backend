package com.kenforum.service;

import com.google.gson.Gson;
import com.kenforum.entity.Comment;
import com.kenforum.entity.Topic;
import com.kenforum.entity.User;
import com.kenforum.repository.CommentRepository;
import com.kenforum.repository.TopicRepository;
import com.kenforum.repository.UserRepository;
import com.kenforum.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Date;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private TopicRepository topicRepository;

    private UserRepository userRepository;

    @Autowired
    public CommentService(final CommentRepository commentRepository, final TopicRepository topicRepository, final UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public Observable<Comment> create(final CommentRequest request) {
        Comment comment = new Comment();
        comment.setCreationDate(new Date());
        comment.setComment(comment.getComment());
        comment.setUser(userRepository.findOne(request.getUserId()));
        comment.setTopic(topicRepository.findOne(request.getTopicId()));

        return Observable.just(commentRepository.save(comment));
    }

    public void delete(final Long id) {
        commentRepository.delete(id);
    }

}
