package com.example.redisstudy.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
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

        String subject = "레디스 테스트 인증 메일 발송 입니다.";
        String text = "인증 코드 입니다. : " + code;

        log.info("인증 코드 : " + code);

        mailService.sendMail(mail, subject, text);
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
