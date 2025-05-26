package com.example.j_pensionat.service;

import com.example.j_pensionat.enums.RoomCategory;
import com.example.j_pensionat.model.Product;
import com.example.j_pensionat.model.Room;
import org.springframework.stereotype.Service;

@Service
public class ProductAvailabilityService {
    public boolean availability(Product product, Room room) {
        return product.getQuantity() == null || product.getQuantity() > 0 && switch (product.getType()) {
            case EXTRA_BED -> room.getCategory() == RoomCategory.DOUBLE;
            default -> true;
        };
    }

    public int maxQuantity(Product product, Room room) {
        return switch (product.getType()) {
            case EXTRA_BED -> room.getSize() / 25;
            default -> 1;
        };
    }
}
