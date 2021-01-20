package com.devup.controller;

import com.devup.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class mycontroller {
    @GetMapping
    public Product seemyname(){
        Product product=new Product();
        product.setId(1);
        product.setName("d");
        return product;
    }
}
