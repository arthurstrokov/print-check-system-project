package com.gmail.arthurstrokov.printcheck.controllers;

import com.gmail.arthurstrokov.printcheck.aspects.InspectingController;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.SaleMDBRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Sale rest controller
 *
 * @author Arthur Strokov
 */
@RestController
@RequiredArgsConstructor
public class SaleController {

    private final SaleMDBRepository repository;

    /**
     * @return List of sales
     */
    @Operation(summary = "Get all sales", description = "Return list of sales")
    @InspectingController
    @GetMapping("/sale")
    List<Sale> getAllSales() {
        return repository.findAll();
    }
}
