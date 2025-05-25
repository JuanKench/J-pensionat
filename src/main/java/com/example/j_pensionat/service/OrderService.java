package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.booking.LineItemDto;
import com.example.j_pensionat.dto.booking.UpdateRequest;
import com.example.j_pensionat.mapper.LineItemMapper;
import com.example.j_pensionat.mapper.OrderMapper;
import com.example.j_pensionat.model.LineItem;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.model.Product;
import com.example.j_pensionat.repository.OrderRepository;
import com.example.j_pensionat.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    //TODO: FIX LATER should not have direct access to repos?
    private final ProductRepository productRepository;
    private final LineItemMapper lineItemMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository, LineItemMapper lineItemMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
        this.lineItemMapper = lineItemMapper;
    }

    public UpdateRequest createUpdateRequest(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(); //TODO: Better error handling.
        return orderMapper.orderToUpdateRequest(order);
    }

    public void update(Long id, UpdateRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));

        List<LineItem> updatedLineItems = request.getLineItems().stream()
                .filter(LineItemDto::isKeep)
                .map(dto -> {
                    Product product = productRepository.findById(dto.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + dto.getProductId()));
                    return lineItemMapper.fromDto(dto, order, product);
                })
                .collect(Collectors.toCollection(ArrayList::new));


        order.getLineItems().clear();
        order.getLineItems().addAll(updatedLineItems);

        order.setNotes(request.getNotes());

        orderRepository.save(order);
    }


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
}
