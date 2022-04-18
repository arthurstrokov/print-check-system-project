package com.gmail.arthurstrokov.printcheck.aspects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SendEmailAspect {
    private final JavaMailSender javaMailSender;

    @Around("@annotation(SendEmail)")
    public Object sendEmail(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getDeclaringTypeName();
        log.info(methodName + " has been executed.");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("arthurstrokov@gmail.com", "arthurstrokov@yandex.ru");

        msg.setSubject("Testing Spring Boot Print Check App");
        msg.setText("Hello.\n" + methodName + " has been executed.");

        log.info("Sending email");
        javaMailSender.send(msg);
        log.info("Email sent");
        return joinPoint.proceed();
    }
}
