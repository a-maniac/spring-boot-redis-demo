package com.api.redis.service.impl;

import com.api.redis.dao.UserJpaRepository;
import com.api.redis.entities.UserEntity;
import com.api.redis.dto.UserDto;
import com.api.redis.mapper.UserMapper;
import com.api.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final RedisTemplate<String,Object> redisTemplate;

    public UserServiceImpl(UserJpaRepository userJpaRepository , RedisTemplate<String,Object> redisTemplate){
        this.userJpaRepository=userJpaRepository;
        this.redisTemplate=redisTemplate;
    }

    private static final String USER_KEY_PREFIX = "user:";

    @Override
    public UserDto getUserById(Long id) {
        String key= USER_KEY_PREFIX+id;
        UserDto cachedUser= (UserDto) redisTemplate.opsForValue().get(key);
        if(cachedUser!=null){
            log.info("User returned from cache");
            return cachedUser;
        }

        UserEntity userEntity= userJpaRepository.findById(1L).orElseThrow(()->new RuntimeException("user not found"));
        UserDto userDto=UserMapper.toDto(userEntity);
        redisTemplate.opsForValue().set(key, userDto, 10, TimeUnit.MINUTES);
        log.info("User returned from DB");
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

        UserEntity newUser= UserMapper.toEntity(user);
        UserEntity saved=userJpaRepository.save(newUser);
        UserDto response=UserMapper.toDto(saved);

        redisTemplate.opsForValue().set(USER_KEY_PREFIX+response.getId(),response,10, TimeUnit.MINUTES);
        return response;
    }


    @Override
    public void deleteUser(Long id) {
        userJpaRepository.deleteById(id);
        redisTemplate.delete(USER_KEY_PREFIX+id);
    }
}
