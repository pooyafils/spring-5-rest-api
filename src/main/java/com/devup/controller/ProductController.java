package com.devup.controller;

import com.devup.model.Product;
import com.devup.repository.CategoryRepository;
import com.devup.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Product> postProduct(@RequestBody Product product){
        productRepository.save(product);
       return ResponseEntity.ok(product) ;
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
public ResponseEntity<Product> singleProduct(@PathVariable int id){

        return ResponseEntity.ok(productRepository.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable int id,@RequestBody Product product){
      Product  productEdit=productRepository.findById(id);
     productEdit.setName(product.getName());
        return ResponseEntity.ok(productRepository.save(productEdit));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteProduct(@PathVariable int id){
        Product product=productRepository.findById(id);
        productRepository.delete(product);
        return ResponseEntity.ok("deleted");
    }
}
