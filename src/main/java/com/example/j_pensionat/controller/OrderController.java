package com.example.j_pensionat.controller;

import com.example.j_pensionat.dto.booking.UpdateRequest;
import com.example.j_pensionat.enums.templatepath.OrderTemplatePath;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.model.Product;
import com.example.j_pensionat.repository.ProductRepository;
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

    public OrderController(OrderService orderService, ProductRepository productRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
    }

    //Resources
    @PutMapping("/{id}")
    public String update(@PathVariable long id,
                         @ModelAttribute UpdateRequest request,
                         Model model) {
        try {
            orderService.update(id, request);

            model.addAttribute("orders", orderService.findAll());
            return OrderTemplatePath.MANAGE.getPath();

        } catch (IllegalStateException ex) {
            model.addAttribute("request", request);
            model.addAttribute("error", ex.getMessage());

            List<Product> allProducts = productRepository.findAll();
            model.addAttribute("allProducts", allProducts);

            try {
                String productsJson = new ObjectMapper().writeValueAsString(allProducts);
                model.addAttribute("productsJson", productsJson);
            } catch (JsonProcessingException e) {
                model.addAttribute("productsJson", "[]");
            }

            return OrderTemplatePath.EDIT.getPath();
        }
    }


    //Views
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) throws JsonProcessingException {
        UpdateRequest request = orderService.createUpdateRequest(id);
        model.addAttribute("request", request);


        List<Product> allProducts = productRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String productsJson = mapper.writeValueAsString(allProducts);

        model.addAttribute("productsJson", productsJson);
        model.addAttribute("allProducts", allProducts);


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
}
