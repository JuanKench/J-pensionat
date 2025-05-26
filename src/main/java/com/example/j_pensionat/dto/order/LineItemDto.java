package com.example.j_pensionat.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineItemDto {
    Long orderId;
    Long productId;
    String productName;
    String productType;
    int quantity;
    int price;

    boolean available;
    int maxQuantity;
}
