package com.example.j_pensionat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoomFilterDto {
    int guests;
    LocalDate startDate;
    LocalDate endDate;
}
