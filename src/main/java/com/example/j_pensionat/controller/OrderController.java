package com.example.j_pensionat.controller;

import com.example.j_pensionat.dto.order.LineItemDto;

import com.example.j_pensionat.dto.order.OrderDto;
import com.example.j_pensionat.enums.templatepath.OrderTemplatePath;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.service.OrderService;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, OrderDto orderDto, Model model) {
        try {
            orderService.update(orderDto);
            model.addAttribute("orders", orderService.findAll());
            return OrderTemplatePath.MANAGE.getPath();
        } catch (Exception ex) {
            model.addAttribute("order", orderDto);
            model.addAttribute("error", ex.getMessage());
            return OrderTemplatePath.EDIT.getPath();
        }
    }

    //Views
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        OrderDto order = orderService.orderDto(id);
        model.addAttribute("order", order);

        return OrderTemplatePath.EDIT.getPath();
    }

    //TODO: Change to use dtos.
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
}
