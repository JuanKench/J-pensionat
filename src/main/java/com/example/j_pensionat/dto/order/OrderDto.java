package com.example.j_pensionat.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    Long orderId;
    String notes;
    List<LineItemDto> lineItems;

    private LocalDate startDate;
    private LocalDate endDate;
    private Long customerId;
    private String customerName;
    private Long roomId;
    private String roomName;

    private boolean hasPaid;
    private boolean cancelled = false;
}