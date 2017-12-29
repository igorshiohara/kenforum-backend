package com.kenforum.controller;

import com.kenforum.entity.User;
import com.kenforum.request.UserRequest;
import com.kenforum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> listAllUsers() {
        return userService.listAll()
                .doOnCompleted( () -> LOGGER.info("Retrieved all users successfully."))
                .doOnError( (err) -> LOGGER.error("Error during retrieve all users.", err))
                .toList().toBlocking().single();
    }

    @RequestMapping(path = "/api/auth", method = RequestMethod.POST)
    public ResponseEntity auth(@RequestBody UserRequest request) {
        List<User> users= userService.getUser(request.getEmail(), request.getPassword())
                .doOnCompleted( () -> LOGGER.info("User retrieved succefully."))
                .doOnError( (err) -> LOGGER.error("Error when trying to retrieve user.", err))
                .toList().toBlocking().single();
        if (users.size() > 0) {
            return ResponseEntity.ok(users.get(0));
        }
        return ResponseEntity.noContent().build();
    }


}
