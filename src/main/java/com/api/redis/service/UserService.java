package com.api.redis.service;

import com.api.redis.entities.UserEntity;
import com.api.redis.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto getUserById(Long id);
    public List<UserDto> findAllUser();
    public UserDto createUser(UserDto user);
    public void deleteUser(Long id);

    public UserDto updateUser(Long userId, UserDto userDto);
}
