package com.example.j_pensionat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

    // Getters and Setters
    public boolean isHasBookings() {
        return hasBookings;
    }

    public void setHasBookings(boolean hasBookings) {
        this.hasBookings = hasBookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
