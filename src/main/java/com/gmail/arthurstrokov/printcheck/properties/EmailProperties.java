package com.gmail.arthurstrokov.printcheck.properties;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EmailProperties {
    private final Environment environment;
    private String emailSendAddress;
    private String emailSubject;
    private String emailText;

    public EmailProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.emailSendAddress = environment.getProperty("email.send.address");
        this.emailSubject = environment.getProperty("email.subject");
        this.emailText = environment.getProperty("email.text");
    }

    public String getEmailSendAddress() {
        return emailSendAddress;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailText() {
        return emailText;
    }
}
