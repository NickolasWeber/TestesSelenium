package com.example.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.produtos.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom queries can be defined here if needed
}