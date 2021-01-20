package com.devup.controller;

import com.devup.model.Product;
import com.devup.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class mycontroller {
    ProductRepository productRepository;

    public mycontroller(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Product seemyname(){
        Product product=new Product();
        product.setId(1);
        product.setName("d");
        return product;
    }
    @PostMapping
    public Product postproduct(@RequestBody Product product){
     return    productRepository.save(product);
    }
}
