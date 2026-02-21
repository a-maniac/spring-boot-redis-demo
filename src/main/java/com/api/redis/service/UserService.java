package com.api.redis.service;

import com.api.redis.entities.UserEntity;
import com.api.redis.dto.User;

import java.util.List;

public interface UserService {

    public UserEntity getUserById(Long id);
    public List<User> findAllUser();
    public User createUser(User user);
    public void deleteUser(Long id);
}
