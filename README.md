# 🚀 Spring Boot Redis Demo

A Spring Boot application demonstrating how to integrate **Redis** using **Spring Data Redis** and `RedisTemplate`.

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
```text
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
```
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

### 🧠 Redis Data Structures Used

#### 1️⃣ String (opsForValue)

Stores a complete object as a single value.

```java
redisTemplate.opsForValue().set("user:1", user);
```

Redis CLI:
```
GET user:1
```

Use case:

- Simple object caching
- Counters
- Tokens


#### 2️⃣ Hash (opsForHash)

Stores multiple objects under one Redis key.

```java
redisTemplate.opsForHash().put("users", user.getId(), user);
```

Redis CLI:
```
HGETALL users
```

Use case:

- Storing multiple users
- Lookup tables
- Grouped data storage

---

## 🚀 How To Run Locally

### 1️⃣ Install Redis

Mac (Homebrew):
```
brew install redis
```

Start Redis:
```
redis-server
```

Verify:
```
redis-cli ping
```

Should return:
```
PONG
```

### 2️⃣ Clone Repository
```
git clone https://github.com/a-maniac/spring-boot-redis-demo.git
cd spring-boot-redis-demo
```

### 3️⃣ Build Project
```
mvn clean install
```

### 4️⃣ Run Application
```
mvn spring-boot:run
```

App runs on:
```
http://localhost:8080
```

---
🔍 Testing Redis Data

Open Redis CLI:
```
redis-cli
```
Check stored keys:
```
KEYS *
```

Check key type:
```
TYPE users
```

Retrieve data:
```
HGETALL users
```
---

## 🧪 Sample Workflow

1. Send request to create user
2. User is saved in Redis using Hash
3. Verify using Redis CLI
4. Fetch user via API

   ---

## 🏗 Why Use Hash Instead of Value?
| Feature                | opsForValue | opsForHash |
| ---------------------- | ----------- | ---------- |
| Single Object          | ✅           | ❌          |
| Store Multiple Objects | ❌           | ✅          |
| Grouped Storage        | ❌           | ✅          |
| Partial Updates        | ❌           | ✅          |

Hash is ideal when storing multiple related objects under one key.
---

## 🧑‍💻 Learning Outcomes

Through this project, you understand:

- Redis data types
- Spring Boot Redis integration
- Serialization concepts
- Redis CLI debugging
- Difference between String and Hash operations

---

## 🔥 Production Use Cases of Redis

1. API response caching
2. JWT token blacklisting
3. Rate limiting
4. Session storage
5. Leaderboards (Sorted Set)
6. Distributed locking




