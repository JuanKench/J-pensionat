package com.example.j_pensionat.dto;

import com.example.j_pensionat.dto.order.LineItemDto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateOrderDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    @NotNull(message = "Kund måste anges")
    private Long customerId;
    private Long roomId;
    private String roomDescription;
    private String roomName;
    @NotNull(message = "Startdatum måste anges")
    private LocalDate startDate;
    @NotNull(message = "Slutdatum måste anges")
    @FutureOrPresent(message = "Slutdatum måste vara idag eller senare")
    private LocalDate endDate;
    private List<Long> productIds;
    private String notes;

    private boolean useExistingCustomer;

    private List<LineItemDto> lineItems;
}
