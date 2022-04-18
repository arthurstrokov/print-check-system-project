package com.gmail.arthurstrokov.printcheck.aspects;

import com.gmail.arthurstrokov.printcheck.properties.EmailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Java send email aspect service class
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SendEmailAspect {
    private final JavaMailSender javaMailSender;
    private final EmailProperties emailProperties;

    /**
     * Method for sending emails
     *
     * @param joinPoint joinPoint
     */
    @Around("@annotation(SendEmail)")
    public Object sendEmail(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getDeclaringTypeName();
        log.info(methodName + " has been executed.");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailProperties.getEmailSendAddress().split(" "));
        msg.setSubject(emailProperties.getEmailSubject());
        msg.setText(emailProperties.getEmailText());

        log.info("Sending email");
        javaMailSender.send(msg);
        log.info("Email sent");
        return joinPoint.proceed();
    }
}
