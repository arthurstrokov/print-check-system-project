package com.gmail.arthurstrokov.printcheck.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputService {
    public static String readFrom(Path fileName) {
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
