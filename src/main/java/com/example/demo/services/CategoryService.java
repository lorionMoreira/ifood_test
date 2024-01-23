package com.example.demo.services;

import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.dto.CatalogDTO;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        return categoryOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: "));
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
    
    @Cacheable(value = "catalogCache", key = "#ownerId")
    public List<CatalogDTO> getAllCategoriesWithProducts(String ownerId) {
        List<Category> categories = categoryRepository.findByOwnerId(ownerId);

        List<CatalogDTO>  catalogs   = categories.stream().map(category -> {
            List<Product> products = productRepository.findByCategoryIdAndOwnerId(category.getId(), ownerId);
            return new CatalogDTO(category, products);
        }).collect(Collectors.toList());

        return catalogs;
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
