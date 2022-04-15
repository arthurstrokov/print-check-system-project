package com.gmail.arthurstrokov.printcheck.controllers;

import com.gmail.arthurstrokov.printcheck.aspects.InspectingController;
import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.service.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Simple controller
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class CheckController {

    private final ProductRepository productRepository;
    private final SaleService saleCalculationService;

    @InspectingController
    @GetMapping("/check")
    public String check(@RequestParam(value = "productId", required = false, defaultValue = "1") String productId,
                        @RequestParam(value = "productSalesAmount", required = false, defaultValue = "1") String productSalesAmount,
                        Model model) {
        Product product = productRepository.findById(Long.parseLong(productId));
        Sale sale = saleCalculationService.saleProduct(product, Integer.valueOf(productSalesAmount));
        model.addAttribute("product", product);
        model.addAttribute("sale", sale);
        log.info(String.valueOf(product));
        log.info(String.valueOf(sale));
        return "check";
    }
}
