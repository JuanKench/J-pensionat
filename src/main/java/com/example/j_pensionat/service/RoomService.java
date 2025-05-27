package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.RoomDto;
import com.example.j_pensionat.dto.RoomFilterDto;
import com.example.j_pensionat.factory.RoomDtoFactory;
import com.example.j_pensionat.model.Room;
import com.example.j_pensionat.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomDtoFactory roomDtoFactory;

    public RoomService(RoomRepository roomRepository, RoomDtoFactory roomDtoFactory) {
        this.roomRepository = roomRepository;
        this.roomDtoFactory = roomDtoFactory;
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    public RoomDto roomDto(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(); //TODO Change
        return roomDtoFactory.dto(room, roomRepository.findUnavailableRoomsWithinRange(LocalDate.now(), LocalDate.now()));
    }

    public List<RoomDto> roomDtos(List<Room> rooms) {
        return roomDtoFactory.create(rooms, roomRepository.findUnavailableRoomsWithinRange(LocalDate.now(), LocalDate.now()));
    }

    public List<RoomDto> allRooms() {
        return roomDtos(roomRepository.findAll());
    }

//    public List<RoomDto> filteredByDateAndGuests(RoomFilterDto filter) {
//        return roomDtos(roomRepository.filteredByDateAndGuests(filter.getStartDate(), filter.getEndDate(), filter.getGuests()));
//    }

    public List<RoomDto> filteredByDateAndGuests(RoomFilterDto filter) {
        List<Room> rooms = roomRepository.filteredByDateAndGuests(
                filter.getStartDate(), filter.getEndDate(), filter.getGuests());

        return roomDtoFactory.create(rooms); // all are available by definition
    }
}
