package com.kenforum.service;

import com.google.common.collect.ImmutableList;
import com.kenforum.entity.Topic;
import com.kenforum.repository.TopicRepository;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(final TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> list() {
        return ImmutableList.copyOf(topicRepository.findAll());
    }

    public Topic create(final Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(final Long id) {
        topicRepository.delete(id);
    }

}
