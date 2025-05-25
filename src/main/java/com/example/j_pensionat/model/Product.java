package com.example.j_pensionat.model;

import com.example.j_pensionat.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private ProductType type;
    private String name;
    private int price;
    private Integer quantity;

    public Product(ProductType type, String name, int price, Integer quantity) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
