package com.example.j_pensionat.dto.booking;

import com.example.j_pensionat.model.LineItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //TODO: Not so good because what if id is changed.
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
    Long orderId;
    String notes;
    List<LineItemDto> lineItems;
}
