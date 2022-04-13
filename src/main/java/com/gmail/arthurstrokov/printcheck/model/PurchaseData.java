package com.gmail.arthurstrokov.printcheck.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseData {
    private List<String> products;
    private String card;
}
