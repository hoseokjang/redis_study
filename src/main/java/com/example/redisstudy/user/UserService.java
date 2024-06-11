package com.example.redisstudy.user;

import com.example.redisstudy.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final RedisTemplate redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final UserRedisRepository userRedisRepository;

    @Transactional
    public User addUser(User user) {
        // save
        User save = userRedisRepository.save(user);

        // find
        Optional<User> result = userRedisRepository.findById(save.getId());

        // Handling, 해당 데이터 존재 시 리턴
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Database has no Data");
        }

    }

    @Transactional(readOnly = true)
    public User getUserById(String reqId) {
        Optional<User> result = userRedisRepository.findById(reqId);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Database has no Data");
        }

    }
}
