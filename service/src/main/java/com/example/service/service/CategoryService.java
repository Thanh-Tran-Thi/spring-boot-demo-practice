package com.example.service.service;

import com.example.service.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createOrUpdateCategory (Category category);
    void deleteCategory(Long id);
    List<Category> listAllCategories();
}
