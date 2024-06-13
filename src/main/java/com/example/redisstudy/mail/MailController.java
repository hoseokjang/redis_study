package com.example.redisstudy.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sendCode")
    public void sendAuthCode(@RequestParam String mail) {
        authService.sendAuthCode(mail);
        log.info("인증번호 발송에 성공하였습니다.");
    }

    @PostMapping("/verifyCode")
    public void verifyAuthCode(@RequestParam String mail, @RequestParam String code) {
        boolean isValid = authService.verifyAuthCode(mail, code);

        if (isValid) {
            log.info("인증에 성공하였습니다.");
        } else {
            log.info("인증에 실패하였습니다.");
        }
    }

}
