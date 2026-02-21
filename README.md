# 🚀 Spring Boot Redis Demo

A production-style Spring Boot application demonstrating how to integrate **Redis** using **Spring Data Redis** and `RedisTemplate`.

This project showcases how to store, retrieve, and manage Java objects in Redis using different Redis data structures like **String** and **Hash**.

---

## 📌 Project Overview

Redis is a high-performance, in-memory key-value store commonly used for:

- Caching
- Session storage
- Rate limiting
- Distributed locking
- Leaderboards
- Microservice data sharing

This project demonstrates:

- Connecting Spring Boot to Redis
- Configuring `RedisTemplate`
- Using `opsForValue()` (String type)
- Using `opsForHash()` (Hash type)
- Storing and retrieving Java objects
- Proper serialization using Jackson

---

## 🛠 Tech Stack

| Technology | Version |
|------------|----------|
| Java | 21 |
| Spring Boot | 3.2.5 |
| Spring Data Redis | 3.x |
| Redis | 7.x |
| Maven | 3.x |
| Lombok | Latest |

---

## 📂 Project Structure
spring-boot-redis-demo
│
├── src/main/java/com/api/redis
│ ├── config
│ │ └── RedisConfig.java
│ ├── controller
│ │ └── UserController.java
│ ├── dao
│ │ └── UserRepository.java
│ ├── models
│ │ └── User.java
│ └── DemoApplication.java
│
├── pom.xml
└── README.md


---

## ⚙️ Redis Configuration

Redis is configured using `RedisTemplate`.

```java
@Bean
public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
}
```
---

## Why Serializer Is Important?

Redis stores data in binary format.
Jackson serializer converts Java objects → JSON before storing.

🧠 Redis Data Structures Used

1️⃣ String (opsForValue)

Stores a complete object as a single value.

```java
redisTemplate.opsForValue().set("user:1", user);
```

Redis CLI:
GET user:1

Use case:

- Simple object caching
- Counters
- Tokens

---
2️⃣ Hash (opsForHash)

Stores multiple objects under one Redis key.

```java
redisTemplate.opsForHash().put("users", user.getId(), user);
```

Redis CLI:
HGETALL users

Use case:

- Storing multiple users
- Lookup tables
- Grouped data storage


