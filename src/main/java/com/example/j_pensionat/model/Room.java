package com.example.j_pensionat.model;

import com.example.j_pensionat.enums.RoomCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    @GeneratedValue
    private long id;
    private RoomCategory category;
    private String description;
    private String name;
    private int size;


    public RoomCategory getRoomCategory(){
        return category;
    }
}
