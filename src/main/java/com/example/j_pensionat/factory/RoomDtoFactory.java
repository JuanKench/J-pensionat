package com.example.j_pensionat.factory;

import com.example.j_pensionat.dto.RoomDto;
import com.example.j_pensionat.enums.RoomCategory;
import com.example.j_pensionat.model.Room;
import com.example.j_pensionat.service.RoomService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomDtoFactory {
    public RoomDto dto(Room room, List<Long> unavailable) {
        boolean available = !unavailable.contains(room.getId());

        return new RoomDto(room.getId(), room.getName(), room.getRoomCategory(), room.getDescription(), room.getSize(), room.getMaxGuests(), available);
    }

    public List<RoomDto> create(List<Room> all, List<Long> unavailable) {
        return all.stream().map(room -> {
            boolean available = !unavailable.contains(room.getId());

            return new RoomDto(room.getId(), room.getName(), room.getRoomCategory(), room.getDescription(), room.getSize(), room.getMaxGuests(), available);
        }).toList();
    }
}
