package com.devup.controller;

import com.devup.model.Category;
import com.devup.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {

/*    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity <List<Category>> getAllCategory(){
        return ResponseEntity.ok(categoryRepository.findAll());

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public ResponseEntity postCategory(@RequestBody Category category){

        return ResponseEntity.ok(categoryRepository.save(category));
    }*/

}
