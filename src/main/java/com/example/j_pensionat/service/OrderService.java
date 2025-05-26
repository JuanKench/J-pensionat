package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.order.OrderDto;
import com.example.j_pensionat.factory.OrderDtoFactory;
import com.example.j_pensionat.model.LineItem;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.model.Product;
import com.example.j_pensionat.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDtoFactory orderDtoFactory;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, OrderDtoFactory orderDtoFactory, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderDtoFactory = orderDtoFactory;
        this.productService = productService;
    }

    public OrderDto orderDto(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        //TODO: Error handling
        return orderDtoFactory.create(order);
    }

    @Transactional
    public void update(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getOrderId()).orElseThrow(); //TODO: Error handling
        order.setNotes(orderDto.getNotes());

        List<LineItem> lineItems = orderDto.getLineItems().stream().peek(lineItemDto -> {
            if(lineItemDto.getQuantity() > lineItemDto.getMaxQuantity()) {
                throw new IllegalStateException("Max quantity exceeded for: " + lineItemDto.getProductName());
            }
        }).filter(lineItemDto -> lineItemDto.getQuantity() > 0).map(lineItemDto -> {
            Product product = productService.findById(lineItemDto.getProductId());
            return new LineItem(order, product, lineItemDto.getQuantity());
        }).collect(Collectors.toCollection(ArrayList::new));

        order.getLineItems().clear();
        order.getLineItems().addAll(lineItems);
        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
}
