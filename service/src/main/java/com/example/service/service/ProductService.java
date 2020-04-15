package com.example.service.service;

import com.example.service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createOrUpdateProduct (Product product);

    void deleteProductById (Long id);

    void deleteProductByIds (Long[] ids);

    Page<Product> getAllProductsWithPagination(Pageable pageable);

    Optional<Product> viewProduct(Long id);
}


