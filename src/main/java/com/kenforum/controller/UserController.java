package com.kenforum.controller;

import com.kenforum.entity.User;
import com.kenforum.request.UserRequest;
import com.kenforum.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/api/users")
    public List<User> listAllUsers() {
        return userService.listAll();
    }

    @RequestMapping(path = "/api/auth", method = RequestMethod.POST)
    public ResponseEntity auth(@RequestBody UserRequest request) {
        List<User> users= userService.getUser(request.getEmail(), request.getPassword());
        if (users.size() > 0) {
            return ResponseEntity.ok(users.get(0));
        }
        return ResponseEntity.noContent().build();
    }


}
