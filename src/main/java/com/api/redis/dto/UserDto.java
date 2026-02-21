package com.api.redis.dto;

import lombok.*;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String phone;
    private String email;
}
