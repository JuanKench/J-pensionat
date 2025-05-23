package com.example.j_pensionat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name ="customer_id")
    @NotNull(message = "Kund måste anges")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @NotNull(message = "Rum måste anges")
    private Room room;

    @NotNull(message = "Startdatum måste anges")
    private LocalDate startDate;

    @NotNull(message = "Slutdatum måste anges")
    @FutureOrPresent(message = "Slutdatum måste vara idag eller senare")
    private LocalDate endDate;

    @Min(value = 1, message = "Minst 1 gäst måste bokas")
    @Max(value = 4, message = "Maximalt 4 gäster per boking")
    private int numGuests;

    @Min(value = 0, message = "Extra sängar kan inte vara mindre än 0")
    @Max(value = 1, message = "Max 1 extra säng tillåten")
    private int extraBed;
}
