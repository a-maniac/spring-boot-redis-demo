package com.api.redis.service;

import com.api.redis.dao.UserJpaRepository;
import com.api.redis.dto.UserDto;
import com.api.redis.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    UserService userService;

    private Long userId;


    @BeforeEach
    void setup() {
        UserEntity user = new UserEntity();
        user.setName("Initial");
        user.setEmail("initial@mail.com");
        user.setPhone("9999999999");

        UserEntity saved = userJpaRepository.save(user);
        userId = saved.getId();
    }

    @Test
    void testConcurrentUpdate() throws InterruptedException {
        int threadCount = 10;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {

            int index = i;

            executor.submit(() -> {
                try {

                    UserDto dto = new UserDto();
                    dto.setName("User-" + index);
                    dto.setEmail("test" + index + "@mail.com");
                    dto.setPhone("9999999999");

                    userService.updateUser(userId, dto);

                } catch (Exception e) {
                    System.out.println("Update failed: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        UserEntity updated = userJpaRepository.findById(userId).orElseThrow();

        System.out.println("Final Name: " + updated.getName());
    }
}
