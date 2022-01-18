package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.PrintCheckApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class InputService {
    private static final Logger log = LoggerFactory.getLogger(InputService.class);

    public String readFromFile(Path fileName) {
        String string = "";
        try {
            string = Files.readString(fileName);
        } catch (IOException e) {
            log.info("File does not exists");
            log.info(e.getMessage());
        }
        return string;
    }

    public static String readFrom(String string) {
        return string;
    }
}
