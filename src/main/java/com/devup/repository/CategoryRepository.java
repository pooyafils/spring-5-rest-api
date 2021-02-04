package com.devup.repository;

import com.devup.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
   // @Query("select  c from Category  c where c.name=?1")
    public Category findByName(String n);
}
