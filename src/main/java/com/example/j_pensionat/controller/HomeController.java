package com.example.j_pensionat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/bookings")
    public String bookings() {
        return "bookings";
    }

    @GetMapping("/costumer")
    public String costumer() {
        return "costumer";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

     @GetMapping("/rooms")
    public String rooms() {
        return "rooms";
     }
}
