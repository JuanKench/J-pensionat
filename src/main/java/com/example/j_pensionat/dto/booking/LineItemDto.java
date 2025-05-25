package com.example.j_pensionat.dto.booking;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LineItemDto {
    Long id;
    Long orderId;
    Long productId;

    String productName;

    Long quantity;
    boolean keep = true;
}