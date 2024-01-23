package com.example.demo.dto;
import java.io.Serializable;
import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Category category;
    private List<Product> products;


}
