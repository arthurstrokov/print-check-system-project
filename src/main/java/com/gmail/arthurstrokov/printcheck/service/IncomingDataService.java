package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.IncomingData;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that present methods witch allowed
 * get incoming values from file or somewhere else
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IncomingDataService {
    private final Gson gson;
    private IncomingData incomingData;

    /**
     * @param fileName name file with store values
     * @return String with incoming values
     */
    public String readIncomingDataFromFile(Path fileName) {
        String string = "";
        try {
            string = Files.readString(fileName);
        } catch (IOException e) {
            log.error("File does not exists");
            log.error(e.getMessage());
        }
        return string;
    }

    /**
     * Method that allowed get incoming values from Json file
     *
     * @param fileName fileName
     * @return Check object with incoming values
     */
    public IncomingData readIncomingDataFromJson(Path fileName) {
        try {
            // 1. JSON file to Java object
            incomingData = gson.fromJson(Files.readString(fileName), IncomingData.class);
        } catch (IOException e) {
            log.error("Something wrong here. You have to know input values format");
            log.error(e.getMessage());
        }
        return incomingData;
    }

    /**
     * Overloaded method that allowed get incoming values from String
     * Could be refactored in more useful method
     *
     * @return String with incoming values
     * @deprecated
     */
    @Deprecated(forRemoval = false)
    public String readIncomingDataFromSomewhere() {
        String string = "";
        log.info("Enter order: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            string = reader.readLine();
        } catch (IOException e) {
            log.error("Something wrong here. You have to know input values format");
            log.error(e.getMessage());
        }
        return string;
    }
}
