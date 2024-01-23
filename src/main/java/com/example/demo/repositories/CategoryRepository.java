package com.example.demo.repositories;

import com.example.demo.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    // Additional query methods can be defined here
    List<Category> findByOwnerId(String ownerId);
}
