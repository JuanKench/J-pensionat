package com.example.j_pensionat.controller;

import com.example.j_pensionat.dto.booking.UpdateRequest;
import com.example.j_pensionat.enums.templatepath.OrderTemplatePath;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Resources
    @PutMapping("/{id}")
    public String update(@PathVariable long id, UpdateRequest request, Model model) {
        orderService.update(id, request);
        model.addAttribute("orders", orderService.findAll());
        return "redirect:/orders/manage";
    }

    //Views
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        UpdateRequest request = orderService.createUpdateRequest(id);
        model.addAttribute("request", request);
        return OrderTemplatePath.EDIT.getPath();
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return OrderTemplatePath.MANAGE.getPath();
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return OrderTemplatePath.DETAILS.getPath();
    }

    @GetMapping("/create")
    public String create() {
        return OrderTemplatePath.CREATE.getPath();
    }






















    //Old

//    @GetMapping("/hej")
//    public String getBookings() {
//        return "bookings";
//    }
//
//    @GetMapping("/list")
//    public List<Order> getBookingsList(@RequestParam int bookingpage) {
//        // TODO
//        return null;
//    }
//
//    @PostMapping("/add")
//    public String addBooking(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) { // ni får ändra detta om ni vill
//        // TODO
//        model.addAttribute("startDate", startDate);
//        model.addAttribute("endDate", endDate);
//        model.addAttribute("costumerId", costumerId);
//        return "bookings";
//    }
//
//    @PutMapping("/{BookingId}")
//    public String changeBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) {
//        // TODO
//        model.addAttribute("startDate", startDate);
//        model.addAttribute("endDate", endDate);
//        model.addAttribute("costumerId", costumerId);
//        return "bookings";
//    }
//
//    @DeleteMapping("/{BookingId}")
//    public String cancelBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long costumerId, Model model) {
//        // TODO
//        model.addAttribute("startDate", startDate);
//        model.addAttribute("endDate", endDate);
//        model.addAttribute("costumerId", costumerId);
//        return "bookings";
//    }

}
