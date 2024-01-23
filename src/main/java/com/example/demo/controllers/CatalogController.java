package com.example.demo.controllers;

import com.example.demo.dto.CatalogDTO;
import com.example.demo.services.CatalogService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogController {
    
    
    private final CatalogService catalogService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public CatalogController(CatalogService catalogService, CategoryService categoryService, ProductService productService) {

        this.catalogService = catalogService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/catalog/{ownerId}")
    public ResponseEntity<List<CatalogDTO>>  getAllCatalogsById(@PathVariable String ownerId) {
        List<CatalogDTO> catalog = categoryService.getAllCategoriesWithProducts(ownerId);
        return ResponseEntity.ok(catalog);

    }
}
