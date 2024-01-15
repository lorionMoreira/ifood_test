package com.example.demo.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

    @MongoId
    private String id;

    private String title;
    private String description;
    private String ownerId;

    // Constructors, getters, and setters are handled by Lombok
}