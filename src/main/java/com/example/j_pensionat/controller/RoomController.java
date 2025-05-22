package com.example.j_pensionat.controller;

import com.example.j_pensionat.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @GetMapping("")
    public List<Room> getRooms() {
        // TODO
        return null;
    }

    @PutMapping("/{Id}")     // ifall dem bygger om ett rum eller något
    public String changeRoom() {
        // TODO
        return null;
    }

    @DeleteMapping("/{Id}")        // ifall dem renoverar byggnaden eller något
    public String deleteRoom() {
        // TODO
        return null;
    }
}
