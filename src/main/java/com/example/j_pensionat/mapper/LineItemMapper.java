package com.example.j_pensionat.mapper;

import com.example.j_pensionat.dto.booking.LineItemDto;
import com.example.j_pensionat.model.LineItem;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LineItemMapper {

    @Named("lineItemToDto")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    LineItemDto toDto(LineItem entity);

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.quantity", target = "quantity")
    @Mapping(source = "product", target = "product")
    @Mapping(source = "order", target = "order")
    LineItem fromDto(LineItemDto dto, Order order, Product product);
}