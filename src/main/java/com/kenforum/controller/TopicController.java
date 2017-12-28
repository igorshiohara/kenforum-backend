package com.kenforum.controller;

import com.kenforum.entity.Topic;
import com.kenforum.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopicController {

    private TopicService topicService;

    public TopicController(final TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping("/api/topics")
    public ResponseEntity list() {
        if (topicService.list().size() > 0) {
            return ResponseEntity.ok(topicService.list());
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/api/topic/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            topicService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/api/topic", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Topic topic) {
        Topic created = topicService.create(topic);
        if (created != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
