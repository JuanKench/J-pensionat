package com.example.j_pensionat.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Förnamn får inte vara tomt")
    @Size(max = 50, message = "Förnamn får vara max 50 tecken")
    @Pattern(regexp = "^[A-Za-zÅÄÖåäö\\s\\-]+$", message = "Förnamnet får endast innehålla bokstäver och mellanslag.")
    private String firstName;

    @NotBlank(message = "Efternamn får inte vara tomt")
    @Size(max = 50, message = "Efternamn får vara max 50 tecken")
    @Pattern(regexp = "^[A-Za-zÅÄÖåäö\\s\\-]+$", message = "Efternamn får endast innehålla bokstäver och mellanslag.")
    private String lastName;

    @NotBlank(message = "Telefonnummer får inte vara tomt")
    @Pattern(
            regexp = "^(\\+46|0)[1-9]\\d{6,9}$",
            message = "Ogiltigt telefonnummer. Ex: 0701234567 eller +46701234567"
    )
    private String phoneNumber;

    @NotBlank(message = "E-post får inte vara tomt")
    @Email(message = "Ogitlig e-poestadress")
    private String email;

    @NotBlank(message = "Adress får inte vara tomt")
    private String address;

    private boolean hasPaid;
    //private String notes; // allergi saker

}
