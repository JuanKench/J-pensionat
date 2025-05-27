package com.example.j_pensionat.controller;

import com.example.j_pensionat.dto.RoomFilterDto;
import com.example.j_pensionat.enums.templatepath.RoomTemplatePath;
import com.example.j_pensionat.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/filter")
    public String filter(RoomFilterDto roomFilterDto, Model model) {
        // TODO
        model.addAttribute("rooms", roomService.filteredByDateAndGuests(roomFilterDto));

        return RoomTemplatePath.LIST.getPath(); //TODO not sure this will work like at all. But... probably?
    }

    @GetMapping("/list")
    public String rooms(Model model) {
        model.addAttribute("rooms", roomService.allRooms());
        model.addAttribute("roomFilterDto", new RoomFilterDto(0, LocalDate.now(), LocalDate.now()));
        return RoomTemplatePath.LIST.getPath();
    }
}
