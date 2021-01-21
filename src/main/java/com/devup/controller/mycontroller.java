package com.devup.controller;

import com.devup.model.Category;
import com.devup.model.Product;
import com.devup.repository.CategoryRepository;
import com.devup.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class mycontroller {
    ProductRepository productRepository;
CategoryRepository categoryRepository;

    public mycontroller(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity< List<Product>> seemyname(){
 return ResponseEntity.ok( productRepository.findAll());
    }
    @PostMapping
    public Product postproduct(@RequestBody Product product){

         return productRepository.save(product);
    }
}
