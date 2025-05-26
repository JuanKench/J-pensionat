package com.example.j_pensionat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Startdatum måste anges")
    private LocalDate startDate;

    @NotNull(message = "Slutdatum måste anges")
    @FutureOrPresent(message = "Slutdatum måste vara idag eller senare")
    private LocalDate endDate;

    @NotNull(message = "Kund måste anges")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull(message = "Rum måste anges")
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems;

    private boolean cancelled = false;

    //TODO: Saknas funktionalitet för det här.
//    @Min(value = 1, message = "Minst 1 gäst måste bokas")
//    @Max(value = 4, message = "Maximalt 4 gäster per boking")
//    private int numGuests;

    public Order(LocalDate startDate, LocalDate endDate, Customer customer, Room room, String notes, List<LineItem> lineItems) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.room = room;
        this.notes = notes;
        this.lineItems = lineItems;
    }


}
