package com.api.redis.service.impl;

import com.api.redis.dao.UserJpaRepository;
import com.api.redis.entities.UserEntity;
import com.api.redis.dto.UserDto;
import com.api.redis.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository){
        this.userJpaRepository=userJpaRepository;
    }


    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity= userJpaRepository.findById(1L).get();
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }


    @Override
    public List<UserDto> findAllUser() {
        List<UserEntity> userDtoList= userJpaRepository.findAll();
        List<UserDto> allUsers= userDtoList.stream().map(req->{
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(req,userDto);
            return userDto;
        }).collect(Collectors.toList());
        return allUsers;
    }


    @Override
    public UserDto createUser(UserDto user) {

        UserEntity newUser= new UserEntity();
        BeanUtils.copyProperties(user,newUser);
        userJpaRepository.save(newUser);
        return user;
    }


    @Override
    public void deleteUser(Long id) {
        userJpaRepository.deleteById(id);
    }
}
