package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.CreateOrderDto;
import com.example.j_pensionat.dto.booking.LineItemDto;
import com.example.j_pensionat.dto.booking.UpdateRequest;
import com.example.j_pensionat.enums.ProductType;
import com.example.j_pensionat.enums.RoomCategory;
import com.example.j_pensionat.mapper.LineItemMapper;
import com.example.j_pensionat.mapper.OrderMapper;
import com.example.j_pensionat.model.*;
import com.example.j_pensionat.repository.CustomerRepository;
import com.example.j_pensionat.repository.OrderRepository;
import com.example.j_pensionat.repository.ProductRepository;
import com.example.j_pensionat.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.ArrayList;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    //TODO: FIX LATER should not have direct access to repos?
    private final ProductRepository productRepository;
    private final LineItemMapper lineItemMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository, LineItemMapper lineItemMapper, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
        this.lineItemMapper = lineItemMapper;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    public UpdateRequest createUpdateRequest(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(); //TODO: Better error handling.
        return orderMapper.orderToUpdateRequest(order);
    }

    public void update(Long id, UpdateRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));

        RoomCategory roomCategory = order.getRoom().getCategory();
        int roomSize = order.getRoom().getSize();

        AtomicInteger totalExtraBedsRequested = new AtomicInteger();

        List<LineItem> updatedLineItems = request.getLineItems().stream()
                .filter(LineItemDto::isKeep)
                .map(dto -> {
                    Product product = productRepository.findById(dto.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + dto.getProductId()));

                    if (product.getType() == ProductType.EXTRA_BED) {
                        if (roomCategory != RoomCategory.DOUBLE) {
                            throw new IllegalStateException("Extra sängar är endast tillåtna i dubbelrum.");
                        }

                        int qty = dto.getQuantity() != null ? dto.getQuantity().intValue() : 0;
                        totalExtraBedsRequested.addAndGet(qty);
                    }


                    return lineItemMapper.fromDto(dto, order, product);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        int maxAllowedBeds = (roomSize >= 75) ? 2 : 1;
        if (totalExtraBedsRequested.get() > maxAllowedBeds) {
            throw new IllegalStateException("Too many extra beds for the room size. Max allowed: " + maxAllowedBeds);
        }

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
