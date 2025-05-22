package com.example.j_pensionat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue
    private long id;


    private LocalDate startDate;
    private LocalDate endDate;

    private int extraBed; // ifall man vill ha en extra säng om man har valt dubbelrum

    private int costumerId; // vem som har bokat
    private int roomId; // vilket rum som är bokat info om rum kommer från Rooms tex(dubbel rum)


}
