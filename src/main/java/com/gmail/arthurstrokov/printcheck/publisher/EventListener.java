package com.gmail.arthurstrokov.printcheck.publisher;

public interface EventListener {
    void sendEmail(String eventType);
}
