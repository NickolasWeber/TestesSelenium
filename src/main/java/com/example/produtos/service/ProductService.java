package com.example.produtos.service;

import com.example.produtos.model.Product; // Assuming there is a Product class
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void saveProduct(Product product) {
        products.add(product);
        // Here you could also implement logic to persist the product to a database
    }
}