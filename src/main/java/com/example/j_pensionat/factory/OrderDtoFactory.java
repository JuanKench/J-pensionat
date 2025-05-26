package com.example.j_pensionat.factory;

import com.example.j_pensionat.dto.order.LineItemDto;
import com.example.j_pensionat.dto.order.OrderDto;
import com.example.j_pensionat.model.LineItem;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderDtoFactory {
    private final LineItemDtoFactory lineItemDtoFactory;
    private final ProductService productService;

    public OrderDtoFactory(LineItemDtoFactory lineItemDtoFactory, ProductService productService) {
        this.lineItemDtoFactory = lineItemDtoFactory;
        this.productService = productService;
    }

    public OrderDto create(Order order) {
        Map<Long, LineItem> existing = order.getLineItems().stream()
                .collect(Collectors.toMap(lineItem -> lineItem.getProduct().getId(), Function.identity()));

        List<LineItemDto> lineItems = productService.allProducts().stream().map(product -> {
            LineItem item = existing.get(product.getId()); //If the product id has already been detected in existing line items

            return item == null ? lineItemDtoFactory.create(product, order, 0) : lineItemDtoFactory.create(product, order, item.getQuantity());
        }).toList();

        return OrderDto.builder()
                .orderId(order.getId())
                .notes(order.getNotes())
                .lineItems(lineItems)
                .build();
    }
}
