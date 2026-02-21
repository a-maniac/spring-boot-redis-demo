package com.api.redis.controller;

import com.api.redis.dao.UserRepository;
import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/post")
    public User createUser(@RequestBody User user){

        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);

    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId){
        return userRepository.getUserById(userId);

    }

    @GetMapping("/getAllUsers")
    public Map<Object,Object> getAllUser(){
        return userRepository.findAllUsers();

    }

    @DeleteMapping("/delete")
    public void getAllUser(@PathVariable String userId){
        userRepository.delete(userId);

    }

}
