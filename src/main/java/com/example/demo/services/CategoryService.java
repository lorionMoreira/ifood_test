package com.example.demo.services;

import com.example.demo.domain.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, Category categoryDetails) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setTitle(categoryDetails.getTitle());
                    category.setDescription(categoryDetails.getDescription());
                    category.setOwnerId(categoryDetails.getOwnerId());
                    return categoryRepository.save(category);
                })
                .orElse(null);
    }

    public boolean deleteCategory(String id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
