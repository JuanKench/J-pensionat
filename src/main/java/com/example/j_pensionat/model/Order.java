package com.example.j_pensionat.model;

import jakarta.persistence.*;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private int customerId; // vem som har bokat
    private int roomId; // vilket rum som är bokat info om rum kommer från Rooms tex(dubbel rum)
    private String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems;

    public Order(LocalDate startDate, LocalDate endDate, int customerId, int roomId, String notes, List<LineItem> lineItems) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.roomId = roomId;
        this.notes = notes;
        this.lineItems = lineItems;
    }
}
