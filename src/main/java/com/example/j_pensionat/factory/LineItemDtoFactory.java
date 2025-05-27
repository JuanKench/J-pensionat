package com.example.j_pensionat.factory;

import com.example.j_pensionat.dto.order.LineItemDto;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.model.Product;
import com.example.j_pensionat.model.Room;
import com.example.j_pensionat.service.ProductAvailabilityService;
import org.springframework.stereotype.Component;

@Component
public class LineItemDtoFactory {
    private final ProductAvailabilityService productAvailabilityService;

    public LineItemDtoFactory(ProductAvailabilityService productAvailabilityService) {
        this.productAvailabilityService = productAvailabilityService;
    }

    public LineItemDto create(Product product, Order order, int quantity) {
        boolean available = productAvailabilityService.availability(product, order.getRoom());
        int maxQuantity = available ? productAvailabilityService.maxQuantity(product, order.getRoom()) : 0;

        return LineItemDto.builder()
                .price(product.getPrice())
                .productId(product.getId())
                .productName(product.getName())
                .productType(product.getType().name())
                .quantity(quantity)
                .orderId(product.getId())
                .available(available)
                .maxQuantity(maxQuantity)
                .build();
    }

    public LineItemDto draft(Product product, Room room) {
        boolean available = productAvailabilityService.availability(product, room);
        int maxQuantity = available ? productAvailabilityService.maxQuantity(product, room) : 0;

        return LineItemDto.builder()
                .price(product.getPrice())
                .productId(product.getId())
                .productName(product.getName())
                .productType(product.getType().name())
                .quantity(0)
                .orderId(product.getId())
                .available(available)
                .maxQuantity(maxQuantity)
                .build();
    }
}
