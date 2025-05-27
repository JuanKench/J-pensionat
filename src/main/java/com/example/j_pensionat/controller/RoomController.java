package com.example.j_pensionat.controller;

import com.example.j_pensionat.dto.RoomFilterDto;
import com.example.j_pensionat.enums.templatepath.RoomTemplatePath;
import com.example.j_pensionat.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String filter(@Valid RoomFilterDto roomFilterDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("rooms", roomService.allRooms());
            return RoomTemplatePath.LIST.getPath();
        }

        if (roomFilterDto.getEndDate().isBefore(roomFilterDto.getStartDate())) {
            model.addAttribute("rooms", roomService.allRooms());
            model.addAttribute("roomFilterDto", roomFilterDto);
            model.addAttribute("dateError", "Slutdatum kan inte vara före startdatum.");
            return RoomTemplatePath.LIST.getPath();
        }

        model.addAttribute("rooms", roomService.filteredByDateAndGuests(roomFilterDto));
        return RoomTemplatePath.LIST.getPath();
    }

    @GetMapping("/list")
    public String rooms(Model model) {
        model.addAttribute("rooms", roomService.allRooms());
        model.addAttribute("roomFilterDto", new RoomFilterDto(0, LocalDate.now(), LocalDate.now()));
        return RoomTemplatePath.LIST.getPath();
    }
}
