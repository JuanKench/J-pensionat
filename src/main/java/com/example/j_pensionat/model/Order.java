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
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems;

    public Order(LocalDate startDate, LocalDate endDate, Customer customer, Room room, String notes, List<LineItem> lineItems) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.room = room;
        this.notes = notes;
        this.lineItems = lineItems;
    }
}
