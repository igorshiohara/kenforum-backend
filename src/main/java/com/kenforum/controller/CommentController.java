package com.kenforum.controller;

import com.kenforum.entity.Comment;
import com.kenforum.entity.Topic;
import com.kenforum.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/api/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            commentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Comment comment) {
        Comment created = commentService.create(comment);
        if (created != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
