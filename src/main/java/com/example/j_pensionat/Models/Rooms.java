package com.example.j_pensionat.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Rooms {

    @Id
    @GeneratedValue
    private long id;

    private Enum roomCategori; // enkel eller dubbel rum

    private String description; // förklaring av rummet behövs antagligen inte

}
