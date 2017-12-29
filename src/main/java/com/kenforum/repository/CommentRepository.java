package com.kenforum.repository;

import com.kenforum.entity.Comment;
import com.kenforum.entity.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByTopic(Topic topic);

}
