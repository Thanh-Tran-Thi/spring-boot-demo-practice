package com.example.service.service;

import com.example.service.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProducts (Product product);

    List<Product> listProducts();
}


