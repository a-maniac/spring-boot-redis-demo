package com.api.redis.service.impl;

import com.api.redis.dao.UserJpaRepository;
import com.api.redis.entities.UserEntity;
import com.api.redis.dto.UserDto;
import com.api.redis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository){
        this.userJpaRepository=userJpaRepository;
    }


    @Override
    public UserEntity getUserById(Long id) {
        return userJpaRepository.findById(1L).get();
    }


    @Override
    public List<UserDto> findAllUser() {
        return List.of();
    }


    @Override
    public UserDto createUser(UserDto user) {
        return null;
    }


    @Override
    public void deleteUser(Long id) {

    }
}
