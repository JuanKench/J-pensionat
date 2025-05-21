package com.example.j_pensionat.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Data
@Entity
public class Costumer {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private boolean hasPaid; //det borde vara här men det finns inget krav på det
    private String email;
    private String address;
    //private String notes; // allergi saker

}
