package com.example.demo.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @MongoId
    private String id;

    private String title;
    private String description;
    private String ownerId;

    // Constructors, getters, and setters are handled by Lombok
}