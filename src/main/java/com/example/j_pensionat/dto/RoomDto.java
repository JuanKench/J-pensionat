package com.example.j_pensionat.dto;

import com.example.j_pensionat.enums.RoomCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private String name;
    private RoomCategory category;
    private String description;
    private int size;
    private int maxGuests;
    private boolean available;
}
