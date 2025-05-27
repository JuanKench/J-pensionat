package com.example.j_pensionat.model;

import com.example.j_pensionat.enums.RoomCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Rumkategori måste anges")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomCategory category;

    @NotBlank(message = "Beskrivning får inte vara tom")
    @Size(max = 255, message = "Beskrivningen får vara max 255 tecken")
    private String description;

    @NotBlank(message = "Namn får inte vara tomt")
    @Size(max = 100, message = "Namnet får vara max 100 tecken")
    @Column(unique = true, nullable = false)
    private String name;

    @Min(value = 10, message = "Rumsstorlek måste vara minst 10 m²")
    private int size;

    @Min(value = 1, message = "Rummet måste kunna ta emot minst en gäst")
    private int maxGuests;

    public RoomCategory getRoomCategory() {
        return category;
    }
}
