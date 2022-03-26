package com.gmail.arthurstrokov.printcheck.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class InputService {
    private static final Logger log = LoggerFactory.getLogger(InputService.class);

    public String readFromSomewhere(Path fileName) {
        String string = "";
        try {
            string = Files.readString(fileName);
        } catch (IOException e) {
            log.info("{} {}", e.getMessage(), "file does not exists");
        }
        return string;
    }

    public String readFromSomewhere() {
        String string = "";
        System.out.println("Enter order: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            string = reader.readLine();
        } catch (IOException e) {
            log.info("{} {}", e.getMessage(), "Something wrong here. You have to know input values format");
        }
        return string;
    }
}
