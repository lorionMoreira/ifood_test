package com.example.demo.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product implements Serializable {

    @MongoId
    private String id;

    private String title;
    private String description;
    private double price;

    @DBRef
    private Category category;
    private String ownerId;

    // Constructors, getters, and setters are handled by Lombok
}