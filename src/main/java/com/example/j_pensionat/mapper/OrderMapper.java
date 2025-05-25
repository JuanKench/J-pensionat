package com.example.j_pensionat.mapper;

import com.example.j_pensionat.dto.booking.UpdateRequest;
import com.example.j_pensionat.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {LineItemMapper.class})
public interface OrderMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookingFromRequest(UpdateRequest request, @MappingTarget Order order);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "lineItems", target = "lineItems", qualifiedByName = "lineItemToDto")
    UpdateRequest orderToUpdateRequest(Order order);
}
