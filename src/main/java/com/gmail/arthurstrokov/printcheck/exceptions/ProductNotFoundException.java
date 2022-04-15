package com.gmail.arthurstrokov.printcheck.exceptions;

/**
 * Custom product exception
 *
 * @author Arthur Strokov
 * @version 1.0
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Could not find product " + id);
    }
}
