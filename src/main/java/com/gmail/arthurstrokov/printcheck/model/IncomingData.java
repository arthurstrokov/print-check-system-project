package com.gmail.arthurstrokov.printcheck.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Arthur Strokov
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IncomingData {
    private List<String> products;
    private String card;
}
