package com.example.j_pensionat.Controllers;

import com.example.j_pensionat.Models.Rooms;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomsController {

    @RequestMapping("Rooms")
    public List<Rooms> getAvailableRooms() {
        // TODO
        return null;
    }

    @RequestMapping("Rooms/notAvailable")   // behövs nog inte
    public List<Rooms> getNotAvailableRooms() {
        //  TODO
        return null;
    }

    @RequestMapping("Rooms/change")     // ifall dem bygger om ett rum eller något
    public String changeRoom() {
        // TODO
        return null;
    }

    @RequestMapping("Rooms/delete")        // ifall dem renoverar byggnaden eller något
    public String deleteRoom() {
        // TODO
        return null;
    }
}
