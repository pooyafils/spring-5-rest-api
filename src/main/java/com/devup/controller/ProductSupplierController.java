package com.devup.controller;

import com.devup.model.Product;
import com.devup.model.ProductSupplier;
import com.devup.repository.ProductSupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductSupplier")
public class ProductSupplierController {
    ProductSupplierRepository productSupplierRepository;

    public ProductSupplierController(ProductSupplierRepository productSupplierRepository) {
        this.productSupplierRepository = productSupplierRepository;
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public ResponseEntity<List<ProductSupplier>> getAllSupplier(){
        return ResponseEntity.ok(productSupplierRepository.findAll());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity postSupplier(@RequestBody ProductSupplier productSupplier){
        return ResponseEntity.ok(productSupplierRepository.save(productSupplier));
    }
}
