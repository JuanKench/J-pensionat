package com.example.j_pensionat.Controllers;

import com.example.j_pensionat.Models.Bookings;
import com.example.j_pensionat.Models.Costumer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingsController {

    @GetMapping("")
    public Bookings getBookings(@RequestParam int bookingpage) {
        // TODO
        return null;
    }

    @GetMapping("/list")
    public List<Bookings> getBookingsList(@RequestParam int bookingpage) {
        // TODO
        return null;
    }

    @PostMapping("/add")
    public Bookings addBooking(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) { // ni får ändra detta om ni vill
        // TODO
        return null;
    }

    @PutMapping("/{BookingId}")
    public String changeBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) {
        // TODO
        return null;
    }

    @DeleteMapping("/{BookingId}")
    public String cancelBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model1) {
        // TODO
        return null;
    }

}
