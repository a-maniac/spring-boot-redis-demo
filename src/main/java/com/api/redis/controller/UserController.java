package com.api.redis.controller;

import com.api.redis.dao.UserRepository;
import com.api.redis.entities.UserEntity;
import com.api.redis.dto.UserDto;
import com.api.redis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return userService.createUser(user);

    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId){
        UserDto currUser= userService.getUserById(userId);
        return currUser;

    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUser(){
        return userService.findAllUser();

    }

    @DeleteMapping("/{userId}")
    public void getAllUser(@PathVariable Long userId){
        userService.deleteUser(userId);

    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        return userService.updateUser(userId,userDto);
    }

}
