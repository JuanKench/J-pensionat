package com.example.j_pensionat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
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
    @Email(message = "Ogiltig e-postadress")
    private String email;

    @NotBlank(message = "Adress får inte vara tomt")
    private String address;

    private boolean hasPaid;
    private boolean hasBookings;
}
