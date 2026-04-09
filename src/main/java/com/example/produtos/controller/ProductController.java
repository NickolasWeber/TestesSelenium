package com.example.produtos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        // Logic to save the product would go here.
        
        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }
}
