package com.gmail.arthurstrokov.printcheck.publisher;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base publisher class that contains the code for managing subscribers and notifying them.
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Service
public class EventManager {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    /**
     * Add new operations to Map
     */
    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /*
     * subscribe for event
     */
    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /*
     *  unsubscribe from event
     */
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    /*
     *  notification that the method has been executed
     */
    public void notify(String eventType) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.sendEmail(eventType);
        }
    }
}
