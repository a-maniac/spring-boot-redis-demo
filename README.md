# Spring Boot Redis Demo

A simple Spring Boot application demonstrating how to use **Redis** as a data store with Spring Data Redis and `RedisTemplate`.

This project shows how to save and retrieve objects (like users) in Redis using Spring Boot.

---

## 🚀 Features

✅ Store Java objects in Redis  
✅ Use `RedisTemplate` with different Redis operations  
   - `opsForValue()` — simple value  
   - `opsForHash()` — hashmap-style storage  
✅ Example REST endpoints (if implemented)  
✅ Designed with Spring Boot 3.x & Java 21  

---

## 📦 Built With

| Technology | Purpose |
|------------|---------|
| Java 21 | Language |
| Spring Boot 3.2.5 | Framework |
| Spring Data Redis | Redis integration |
| Redis | In-memory key-value database |
| Maven | Build tool |
| Lombok | Boilerplate reduction |

---

## 📋 Prerequisites

Before running the project, make sure you have:

✔ **Java 21** installed  
✔ **Maven** installed  
✔ **Redis server** running locally:

```bash
redis-server
