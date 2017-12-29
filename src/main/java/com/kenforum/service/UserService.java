package com.kenforum.service;

import com.google.common.collect.ImmutableList;
import com.kenforum.entity.User;
import com.kenforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<User> listAll() {
        return Observable.from(ImmutableList.copyOf(userRepository.findAll()));
    }

    public Observable<User> getUser(String email, String password) {
        return Observable.from(userRepository.findByEmailAndPassword(email, password));
    }

}
