package com.example.demo.repositories;

import com.example.demo.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // Additional query methods can be defined here
    List<Product> findByCategoryIdAndOwnerId(String categoryId, String ownerId);

}