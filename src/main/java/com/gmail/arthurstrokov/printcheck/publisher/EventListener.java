package com.gmail.arthurstrokov.printcheck.publisher;

/**
 * Subscriber interface
 *
 * @author Arthur Strokov
 * @version 1.0
 */
public interface EventListener {
    void sendEmail(String eventType);
}
