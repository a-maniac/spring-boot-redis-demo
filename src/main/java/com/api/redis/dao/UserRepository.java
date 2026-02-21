package com.api.redis.dao;

import com.api.redis.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String KEY="USER";

    public UserDto save(UserDto user){

        redisTemplate.opsForHash().put(KEY,user.getId(),user);
        return user;
    }

    public UserDto getUserById(String userId){
        return (UserDto) redisTemplate.opsForHash().get(KEY, userId);
    }

    public Map<Object,Object> findAllUsers(){
        return redisTemplate.opsForHash().entries(KEY);
    }

    public void delete(String userId){
        redisTemplate.opsForHash().delete(KEY,userId);
    }
}
