package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.journalApp.JournalApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = JournalApplication.class)
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Disabled
    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email","gmail@email.com");
        Object salary = redisTemplate.opsForValue().get("email");
        int a = 1;
    }
}