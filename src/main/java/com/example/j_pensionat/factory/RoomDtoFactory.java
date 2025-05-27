package com.example.j_pensionat.factory;

import com.example.j_pensionat.dto.RoomDto;
import com.example.j_pensionat.model.Room;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RoomDtoFactory {

    public RoomDto dto(Room room, List<Long> unavailable) {
        boolean available = !unavailable.contains(room.getId());
        return dto(room, available);
    }

    public RoomDto dto(Room room, boolean available) {
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getRoomCategory(),
                room.getDescription(),
                room.getSize(),
                room.getMaxGuests(),
                available
        );
    }

    public List<RoomDto> create(List<Room> all, List<Long> unavailable) {
        return all.stream()
                .map(room -> dto(room, !unavailable.contains(room.getId())))
                .toList();
    }

    public List<RoomDto> create(List<Room> rooms) {
        return rooms.stream()
                .map(room -> dto(room, true))
                .toList();
    }
}
