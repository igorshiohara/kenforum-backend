package com.kenforum.service;

import com.google.common.collect.ImmutableList;
import com.kenforum.entity.User;
import com.kenforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll() {
        return ImmutableList.copyOf(userRepository.findAll());
    }

    public List<User> getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
