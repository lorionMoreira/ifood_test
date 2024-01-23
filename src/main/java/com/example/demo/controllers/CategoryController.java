package com.example.demo.controllers;

import com.example.demo.domain.Category;
import com.example.demo.dto.MessageCaterogy;
import com.example.demo.services.CategoryService;
import com.example.demo.services.RabbitMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    private RabbitMQProducerService producer;

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.createCategory(category);

        /*
        MessageCaterogy message = new MessageCaterogy();
        message.setOwner(savedCategory.getOwnerId());
        message.setIdCategory(savedCategory.getId());
        */

        producer.sendMessage(savedCategory.getOwnerId());
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody Category categoryDetails) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);

        // depois, mudar a implementation disso
        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        }
        producer.sendMessage(updatedCategory.getOwnerId());
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        // implement here the producer
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
