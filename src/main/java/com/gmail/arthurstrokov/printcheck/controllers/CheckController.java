package com.gmail.arthurstrokov.printcheck.controllers;

import com.gmail.arthurstrokov.printcheck.model.Product;
import com.gmail.arthurstrokov.printcheck.model.Sale;
import com.gmail.arthurstrokov.printcheck.repository.ProductRepository;
import com.gmail.arthurstrokov.printcheck.service.SaleCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleCalculationService saleCalculationService;

    @GetMapping("/check")
    public String check(@RequestParam(value = "productId", required = false, defaultValue = "1") String productId,
                        @RequestParam(value = "productSalesAmount", required = false, defaultValue = "1") String productSalesAmount,
                        Model model) {
        Product product = productRepository.findById(Long.parseLong(productId));
        Sale sale = saleCalculationService.saleProduct(product, Long.valueOf(productSalesAmount));
        model.addAttribute("product", product);
        model.addAttribute("sale", sale);
        System.out.println(product);
        System.out.println(sale);
        return "check";
    }
}
