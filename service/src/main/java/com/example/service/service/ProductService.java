package com.example.service.service;

import com.example.service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product createOrUpdateProduct (Product product);

    List<Product> listProducts();

    void deleteProductById (Long id);

    Page<Product> getAllProductsWithPagination(Pageable pageable);
}


