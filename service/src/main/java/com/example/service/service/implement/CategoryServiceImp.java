package com.example.service.service.implement;

import com.example.service.entity.Category;
import com.example.service.repository.CategoryRepository;
import com.example.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category createOrUpdateCategory(Category category) {
        if (category.getId() == null) {
            return repository.save(category);
        } else {
            Optional<Category> catId = repository.findById(category.getId());
            if (catId.isPresent()) {
                Category currentCat = catId.get();
                currentCat.setName(category.getName());
                currentCat.setStatus(category.getStatus());
            }
            return null;
        }
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public List<Category> listAllCategories() {
        return null;
    }
}
