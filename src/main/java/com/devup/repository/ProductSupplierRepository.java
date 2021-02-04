package com.devup.repository;

import com.devup.model.Category;
import com.devup.model.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier,Integer> {
    public ProductSupplier findByName(String n);

}
