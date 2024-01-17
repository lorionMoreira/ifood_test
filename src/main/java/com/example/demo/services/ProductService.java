package com.example.demo.services;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(String id, Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Update the fields of the product with productDetails
            product.setTitle(productDetails.getTitle());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            product.setOwnerId(productDetails.getOwnerId());

            // faltou o tratamento se category realmente existe !


            // Save the updated product
            return productRepository.save(product);
        } else {
            // Handle the case where the product is not found
            return null;
        }
    }

    public Product saveProduct(Product product) {

        // faltou o tratamento se category realmente existe !
        return productRepository.save(product);
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}