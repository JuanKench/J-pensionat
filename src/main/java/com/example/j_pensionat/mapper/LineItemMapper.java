package com.example.j_pensionat.mapper;

import com.example.j_pensionat.dto.order.LineItemDto;
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
    @Mapping(source = "product.type", target = "productType")
    LineItemDto toDto(LineItem entity);
}