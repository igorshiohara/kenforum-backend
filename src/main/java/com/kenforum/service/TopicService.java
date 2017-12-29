package com.kenforum.service;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.kenforum.entity.Topic;
import com.kenforum.entity.User;
import com.kenforum.repository.TopicRepository;
import com.kenforum.request.TopicRequest;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Date;
import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(final TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Observable<Topic> list() {
        return Observable.from(ImmutableList.copyOf(topicRepository.findAll()));
    }

    public Observable<Topic> create(final TopicRequest request) {
        Topic topic = new Topic();
        topic.setCreationDate(new Date());
        topic.setTitle(request.getTitle());
        topic.setDescription(request.getDescription());
        topic.setUser(new Gson().fromJson(request.getUser(), User.class));

        return Observable.just(topicRepository.save(topic));
    }

    public Observable<Topic> get(final Long id) {
        return Observable.just(topicRepository.findOne(id));
    }

    public void delete(final Long id) {
        topicRepository.delete(id);
    }

}
