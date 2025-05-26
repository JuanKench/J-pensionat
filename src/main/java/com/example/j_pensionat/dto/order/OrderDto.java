package com.example.j_pensionat.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //TODO: Not so good because what if id is changed.
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    Long orderId;
    String notes;
    List<LineItemDto> lineItems;
}