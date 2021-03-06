package com.kenforum.controller;

import com.kenforum.entity.Comment;
import com.kenforum.request.CommentRequest;
import com.kenforum.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/api/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    public ResponseEntity<Comment> create(@RequestBody CommentRequest comment) {
        Comment created = commentService.create(comment)
                .doOnCompleted( () -> LOGGER.info("Created comment successfully."))
                .doOnError( (err) -> LOGGER.error("Fail to post a comment.", err))
                .toBlocking().single();
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(value = "/api/comment/topic/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable Long id) {
        List<Comment> comments = commentService.findByTopicId(id)
                .doOnCompleted( () -> LOGGER.info("Comments retrieved successfully."))
                .doOnError( (err) -> LOGGER.error("Fail to retrieve comments.", err))
                .toList().toBlocking().single();
        if (comments.size() > 0) {
            return ResponseEntity.ok(comments);
        }
        return ResponseEntity.noContent().build();
    }

}
