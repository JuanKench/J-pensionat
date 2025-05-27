package com.example.j_pensionat.controller;

import com.example.j_pensionat.dto.CreateOrderDto;
import com.example.j_pensionat.dto.order.OrderDto;
import com.example.j_pensionat.enums.templatepath.OrderTemplatePath;
import com.example.j_pensionat.service.CustomerServiceImpl;
import com.example.j_pensionat.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerServiceImpl customerService;

    public OrderController(OrderService orderService, CustomerServiceImpl customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    //Resources
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, OrderDto orderDto, Model model) {
        try {
            orderService.update(orderDto);
            model.addAttribute("orders", orderService.orderDtos());
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
        model.addAttribute("order", orderService.orderDto(id));

        return OrderTemplatePath.EDIT.getPath();
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        model.addAttribute("orders", orderService.orderDtos());
        return OrderTemplatePath.MANAGE.getPath();
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.orderDto(id));
        return OrderTemplatePath.DETAILS.getPath();
    }

    @GetMapping("/create")
    public String create(Model model, @RequestParam Long roomId) {
        model.addAttribute("createOrderDto", orderService.createOrderDto(roomId));
        model.addAttribute("customers", customerService.customerDtos());
        return OrderTemplatePath.CREATE.getPath();
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute("createOrderDto") @Valid CreateOrderDto dto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.customerDtos());
            return OrderTemplatePath.CREATE.getPath();
        }

        try {
            orderService.createOrder(dto);
            model.addAttribute("orders", orderService.orderDtos());
            return OrderTemplatePath.MANAGE.getPath();
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("createOrderDto", dto);
            model.addAttribute("customers", customerService.customerDtos());
            return OrderTemplatePath.CREATE.getPath();
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/manage";
    }
}
