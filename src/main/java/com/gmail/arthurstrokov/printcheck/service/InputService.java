package com.gmail.arthurstrokov.printcheck.service;

import com.gmail.arthurstrokov.printcheck.model.PurchaseData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 */
@Service
public class InputService {
    private static final Logger log = LoggerFactory.getLogger(InputService.class);

    /**
     * @param fileName name file with store values
     * @return String with incoming values
     */
    public String readFromSomewhere(Path fileName) {
        String string = "";
        try {
            string = Files.readString(fileName);
        } catch (IOException e) {
            log.error("{} {}", e.getMessage(), "file does not exists");
        }
        return string;
    }

    /**
     * Method that allowed get incoming values from Json file
     *
     * @return Check object with incoming values
     */
    public PurchaseData readFromJson(Path fileName) {
        Gson gson = new Gson();
        PurchaseData purchaseData = new PurchaseData();
        try {
            // 1. JSON file to Java object
            purchaseData = gson.fromJson(Files.readString(fileName), PurchaseData.class);
        } catch (IOException e) {
            log.error("{} {}", e.getMessage(), "Something wrong here. You have to know input values format");
        }
        return purchaseData;
    }

    /**
     * Overloaded method that allowed get incoming values from String
     * Could be refactored in more useful method
     *
     * @return String with incoming values
     * @deprecated
     */
    public String readFromSomewhere() {
        String string = "";
        System.out.println("Enter order: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            string = reader.readLine();
        } catch (IOException e) {
            log.error("{} {}", e.getMessage(), "Something wrong here. You have to know input values format");
        }
        return string;
    }
}
