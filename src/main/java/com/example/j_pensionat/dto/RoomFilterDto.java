package com.example.j_pensionat.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoomFilterDto {
    private int guests;
    @FutureOrPresent(message = "Startdatum måste vara idag eller senare")
    @NotNull
    private LocalDate startDate;
    @FutureOrPresent(message = "Slutdatum måste vara idag eller senare")
    @NotNull
    private LocalDate endDate;
}
