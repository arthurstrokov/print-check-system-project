package com.gmail.arthurstrokov.printcheck.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class InputService {
    public String readFromFile(Path fileName) {
        String string = "";
        try {
            string = Files.readString(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String readFrom(String string) {
        return string;
    }
}
