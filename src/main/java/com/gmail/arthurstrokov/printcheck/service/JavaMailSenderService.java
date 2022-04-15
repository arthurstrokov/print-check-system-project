package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.publisher.EventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Java mail service class
 *
 * @author Arthur Strokov
 * @version 1.0
 */

@Slf4j
@RequiredArgsConstructor
@Service
@PropertySource("classpath:application-mail.properties")
public class JavaMailSenderService implements EventListener {
    private final JavaMailSender javaMailSender;

    /**
     * Method for sending emails
     *
     * @param eventType event name
     */
    @Override
    public void sendEmail(String eventType) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("arthurstrokov@gmail.com", "arthurstrokov@yandex.ru");

        msg.setSubject("Testing Spring Boot Print Check App");
        msg.setText("Hello.\nCheck has been created.");

        log.info("Sending email");
        javaMailSender.send(msg);
        log.info("Email sent");
        log.info(eventType);
    }
}
