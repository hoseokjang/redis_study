package com.example.redisstudy.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MailService mailService;

    public String generateAuthCode() {

        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 6자리 숫자 생성
        return String.valueOf(code);
    }

    public void sendAuthCode(String mail) {
        String code = generateAuthCode();
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(mail, code, 5, TimeUnit.MINUTES); // 인증번호 5분동안 저장
    }

    public boolean verifyAuthCode(String mail, String code) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String storedCode = vop.get(mail);

        if (storedCode != null && storedCode.equals(code)) {
            redisTemplate.delete(mail); // 인증 완료 후 코드 삭제
            return true;
        } else {
            return false;
        }
    }

}
