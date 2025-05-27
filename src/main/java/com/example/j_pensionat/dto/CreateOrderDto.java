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


//        public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }
//
//    public Long getRoomId() {
//        return roomId;
//    }
//
//    public void setRoomId(Long roomId) {
//        this.roomId = roomId;
//    }
//
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }
//
//    public List<Long> getProductIds() {
//        return productIds;
//    }
//
//    public void setProductIds(List<Long> productIds) {
//        this.productIds = productIds;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public boolean isUseExistingCustomer() {
//        return useExistingCustomer;
//    }
//
//    public void setUseExistingCustomer(boolean useExistingCustomer) {
//        this.useExistingCustomer = useExistingCustomer;
//    }

}
