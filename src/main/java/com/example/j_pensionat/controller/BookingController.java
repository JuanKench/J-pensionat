package com.example.j_pensionat.controller;

import com.example.j_pensionat.model.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @GetMapping("/hej")
    public String getBookings() {
        return "bookings";
    }

    @GetMapping("/list")
    public List<Booking> getBookingsList(@RequestParam int bookingpage) {
        // TODO
        return null;
    }

    @PostMapping("/add")
    public String addBooking(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) { // ni får ändra detta om ni vill
        // TODO
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("costumerId", costumerId);
        return "bookings";
    }

    @PutMapping("/{BookingId}")
    public String changeBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) {
        // TODO
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("costumerId", costumerId);
        return "bookings";
    }

    @DeleteMapping("/{BookingId}")
    public String cancelBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) {
        // TODO
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("costumerId", costumerId);
        return "bookings";
    }

}
