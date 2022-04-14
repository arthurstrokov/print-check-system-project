package com.gmail.arthurstrokov.printcheck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@PropertySource("classpath:application-mail.properties")
public class JavaMailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("arthurstrokov@gmail.com", "arthurstrokov@yandex.ru");

        msg.setSubject("Testing Spring Boot Print Check App");
        msg.setText("Hello.\nCheck has been created.");

        log.info("Sending email");
        javaMailSender.send(msg);
        log.info("Email sent");
    }
}