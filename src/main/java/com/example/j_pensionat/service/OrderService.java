package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.CreateOrderDto;
import com.example.j_pensionat.dto.order.OrderDto;
import com.example.j_pensionat.enums.ProductType;
import com.example.j_pensionat.enums.RoomCategory;
import com.example.j_pensionat.factory.OrderDtoFactory;
import com.example.j_pensionat.mapper.OrderMapper;
import com.example.j_pensionat.model.*;
import com.example.j_pensionat.repository.CustomerRepository;
import com.example.j_pensionat.repository.OrderRepository;
import com.example.j_pensionat.repository.ProductRepository;
import com.example.j_pensionat.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.ArrayList;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDtoFactory orderDtoFactory;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderDtoFactory orderDtoFactory, ProductService productService, OrderMapper orderMapper, CustomerRepository customerRepository, RoomRepository roomRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDtoFactory = orderDtoFactory;
        this.productService = productService;
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.productRepository = productRepository;
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

    public Order createOrder(CreateOrderDto dto) {
        Customer customer;

        if (dto.isUseExistingCustomer() && dto.getCustomerId() != null) {
            customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Kunden finns inte"));
        } else {
            customer = new Customer();
            customer.setFirstName(dto.getFirstName());
            customer.setLastName(dto.getLastName());
            customer.setEmail(dto.getEmail());
            customer.setPhoneNumber(dto.getPhoneNumber());
            customer.setAddress(dto.getAddress());
            customer = customerRepository.save(customer);
        }

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Rummet finns inte"));

        List<Product> products = productRepository.findAllById(dto.getProductIds());

        if (room.getRoomCategory() == RoomCategory.SINGLE) {
            boolean hasExtraBed = products.stream()
                    .anyMatch(p -> p.getProductType() == ProductType.EXTRA_BED);
            if (hasExtraBed) {
                throw new IllegalArgumentException("Extra säng får endast bokas med dubbelrum.");
            }
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setRoom(room);
        order.setStartDate(dto.getStartDate());
        order.setEndDate(dto.getEndDate());
        order.setNotes(dto.getNotes());

        List<LineItem> lineItems = products.stream()
                .map(product -> {
                    LineItem item = new LineItem();
                    item.setProduct(product);
                    item.setOrder(order);
                    item.setQuantity(1);
                    return item;
                })
                .collect(Collectors.toList());

        order.setLineItems(lineItems);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bokningen finns inte"));
        orderRepository.delete(order);
    }



}
