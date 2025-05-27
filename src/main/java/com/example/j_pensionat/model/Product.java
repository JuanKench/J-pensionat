package com.example.j_pensionat.model;

import com.example.j_pensionat.enums.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Produkttyp måste anges")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType type;

    @NotBlank(message = "Produktnamn får inte vara tomt")
    @Size(max = 100, message = "Produktnamnet får vara max 100 tecken")
    @Column(nullable = false)
    private String name;

    @Min(value = 0, message = "Priset måste vara minst 0")
    @Column(nullable = false)
    private int price;

    @Min(value = 0, message = "Antalet måste vara minst 0")
    @Column(nullable = true)
    private Integer quantity;

    public Product(ProductType type, String name, int price, Integer quantity) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return this.type;
    }
}
