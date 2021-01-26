package com.devup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn
    private Category category;
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ProductSupplier productSupplier;

   // private int available;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductSupplier getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(ProductSupplier productSupplier) {
        this.productSupplier = productSupplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", productSupplier=" + productSupplier +
                '}';
    }
}
