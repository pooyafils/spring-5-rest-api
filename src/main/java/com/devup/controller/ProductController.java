package com.devup.controller;

import com.devup.exceptions.ErrorHandling;
import com.devup.exceptions.StudentValidation;
import com.devup.model.Category;
import com.devup.model.Product;
import com.devup.model.ProductSupplier;
import com.devup.repository.CategoryRepository;
import com.devup.repository.ProductRepository;
import com.devup.repository.ProductSupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {
    List<String> errors;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductSupplierRepository productSupplierRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository, ProductSupplierRepository productSupplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productSupplierRepository = productSupplierRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Product> postProduct(@RequestBody Product product) {
        String name = product.getCategory().getName().toString();
        Category c = categoryRepository.findByName(name);
        product.setCategory(c);
        String supliername = product.getProductSupplier().getName();
        ProductSupplier p = productSupplierRepository.findByName(supliername);
        product.setProductSupplier(p);
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> singleProduct(@PathVariable int id) {
        if (productRepository.findById(id) == null) {
            throw new StudentValidation("student with this id not founded "+id);
        }
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable int id, @Valid @RequestBody Product product, BindingResult bindingResult) {

        Product productEdit = productRepository.findById(id);
        productEdit.setName(product.getName());
        productEdit.setPrice(product.getPrice());
        return ResponseEntity.ok(productRepository.save(productEdit));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteProduct(@PathVariable int id) {
        Product product = productRepository.findById(id);
        productRepository.delete(product);
        return ResponseEntity.ok("deleted");
    }
    @ExceptionHandler
    public ResponseEntity<ErrorHandling> validationErrorHandler(StudentValidation studentValidation) {
        ErrorHandling errorHandling=new ErrorHandling();
        errorHandling.setStatus(HttpStatus.NOT_FOUND.value());
        errorHandling.setError(studentValidation.getMessage());
        errorHandling.setTimestames(System.currentTimeMillis());
        return new ResponseEntity<>(errorHandling,HttpStatus.BAD_REQUEST);
    }

}
