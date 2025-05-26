package com.example.j_pensionat.controller;

import com.example.j_pensionat.dto.CreateOrderDto;
import com.example.j_pensionat.dto.order.OrderDto;
import com.example.j_pensionat.enums.templatepath.OrderTemplatePath;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.repository.CustomerRepository;
import com.example.j_pensionat.repository.ProductRepository;
import com.example.j_pensionat.repository.RoomRepository;
import com.example.j_pensionat.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public OrderController(OrderService orderService, ProductRepository productRepository, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    //Resources
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
    public String create(Model model) {
        model.addAttribute("createOrderDto", new CreateOrderDto());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return OrderTemplatePath.CREATE.getPath();
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute("createOrderDto") CreateOrderDto dto, Model model) {
        try {
            orderService.createOrder(dto);
            return "redirect:" + OrderTemplatePath.MANAGE.getPath();
        }catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("createOrderDto", dto); // ← lägg till detta!
            model.addAttribute("customers", customerRepository.findAll());
            model.addAttribute("rooms", roomRepository.findAll());
            model.addAttribute("products", productRepository.findAll());
            return OrderTemplatePath.CREATE.getPath();
        }

    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/manage";
    }
}
