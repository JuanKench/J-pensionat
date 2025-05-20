package com.example.j_pensionat.Controllers;

import com.example.j_pensionat.Models.Bookings;
import com.example.j_pensionat.Models.Costumer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingsController {


    @RequestMapping("/Bookings/list")
    public List<Bookings> getBookingsList(@RequestParam int bookingpage) {
        // TODO
        return null;
    }

    @RequestMapping("Bookings")
    public Bookings getBookings(@RequestParam int bookingpage) {
        // TODO
        return null;
    }

    @RequestMapping("/Bookings/add")
    public Bookings addBooking(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId) { // ni får ändra detta om ni vill
        // TODO
        return null;
    }

    @RequestMapping("Bookings/change/{BookingId}")
    public String changeBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId) {
        // TODO
        return null;
    }

    @RequestMapping("Bookings/cancel/{BookingId}")
    public String cancelBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId) {
        // TODO
        return null;
    }

}
