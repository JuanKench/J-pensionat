package com.example.j_pensionat.mapper;

import com.example.j_pensionat.dto.order.OrderDto;
import com.example.j_pensionat.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {LineItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "lineItems", target = "lineItems", qualifiedByName = "lineItemToDto")
    OrderDto toDto(Order order);
}
