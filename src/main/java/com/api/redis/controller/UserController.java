package com.api.redis.controller;

import com.api.redis.dao.UserRepository;
import com.api.redis.entities.UserEntity;
import com.api.redis.dto.UserDto;
import com.api.redis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/post")
    public UserDto createUser(@RequestBody UserDto user){
        return userRepository.save(user);

    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId){
        UserEntity currUser= userService.getUserById(userId);
        return currUser;

    }

    @GetMapping("/getAllUsers")
    public Map<Object,Object> getAllUser(){
        return userRepository.findAllUsers();

    }

    @DeleteMapping("/{userId}")
    public void getAllUser(@PathVariable String userId){
        userRepository.delete(userId);

    }

}
