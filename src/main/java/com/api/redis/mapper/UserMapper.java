package com.api.redis.mapper;

import com.api.redis.dto.UserDto;
import com.api.redis.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toDto(UserEntity userEntity){
        if(userEntity==null) return null;

        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .email(userEntity.getEmail())
                .build();

    }

    public static UserEntity toEntity(UserDto userDto){

        if(userDto==null) return null;

        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .build();

    }
}
