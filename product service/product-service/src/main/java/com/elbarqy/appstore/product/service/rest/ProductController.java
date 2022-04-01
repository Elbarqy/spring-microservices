package com.elbarqy.appstore.product.service.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products") //http://localhost:8080/products
public class ProductController {
    @PostMapping
    public String createProduct() {
        return "HTTP Handling";
    }
    @GetMapping
    public String getProduct(){
        return "HTTP GET handle String";
    }
}
