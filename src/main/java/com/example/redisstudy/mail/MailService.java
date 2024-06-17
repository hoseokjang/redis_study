package com.example.redisstudy.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String text) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            mimeMessage.addRecipients(MimeMessage.RecipientType.TO, to);
            mimeMessage.setText(text);
            mimeMessage.setSubject(subject);
            mimeMessage.setFrom("hoseok210@naver.com");
        } catch (MessagingException e ) {
            log.error(e.getMessage());
        }

        javaMailSender.send(mimeMessage);
    }

}
