package com.api.redis.models;

import lombok.*;

import java.io.Serializable;

@Data
public class User  implements Serializable {

    private String id;
    private String name;
    private String phone;
    private String email;
}
