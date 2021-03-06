package com.gmail.arthurstrokov.printcheck.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Java Email Properties class
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Component
public class EmailProperties {
    private final Environment environment;
    private String emailSendAddress;
    private String emailSubject;
    private String emailText;

    @Autowired
    public EmailProperties(Environment environment) {
        this.environment = environment;
    }

    /**
     * Spring calls the methods annotated with @PostConstruct only once,
     * just after the initialization of bean properties.
     */
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
