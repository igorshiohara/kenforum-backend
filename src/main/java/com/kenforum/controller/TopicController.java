package com.kenforum.controller;

import com.kenforum.entity.Topic;
import com.kenforum.request.TopicRequest;
import com.kenforum.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TopicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

    private TopicService topicService;

    public TopicController(final TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping("/api/topics")
    public ResponseEntity list() {

        List<Topic> topics = topicService.list()
                .doOnCompleted( () -> LOGGER.info("Success retrieving all topics."))
                .doOnError( (err) -> LOGGER.error("Error when trying to find topics: ", err))
                .toList().toBlocking().single();

        if (topics.size() > 0) {
            return ResponseEntity.ok(topics);
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
    public ResponseEntity<Topic> create(@RequestBody TopicRequest request) {
        Topic created = topicService.create(request)
                .doOnCompleted( () -> LOGGER.info("Topic created successfully."))
                .doOnError( (err) -> LOGGER.error("An error occurred when trying to create a topic.", err))
                .toBlocking().single();
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(value = "/api/topic/{id}", method = RequestMethod.GET)
    public ResponseEntity<Topic> get(@PathVariable Long id) {
        Topic topic = topicService.get(id)
                .doOnCompleted( () -> LOGGER.info("Success call retrieving a single topic."))
                .doOnError( (err) -> LOGGER.error("Error retrieving topic."))
                .toBlocking().single();

        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

}
