package com.example.j_pensionat;

import com.example.j_pensionat.enums.ProductType;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.model.Product;
import com.example.j_pensionat.model.LineItem;
import com.example.j_pensionat.repository.ProductRepository;
import com.example.j_pensionat.repository.LineItemRepository;
import com.example.j_pensionat.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class JPensionatApplication {
    public static void main(String[] args) {
        SpringApplication.run(JPensionatApplication.class, args);
    }

    @Bean
    public CommandLineRunner seed(OrderRepository orderRepository, ProductRepository productRepository, LineItemRepository lineItemRepository) {
        return args -> {
            orderRepository.deleteAll();
            lineItemRepository.deleteAll();
            productRepository.deleteAll();

            Product add1 = productRepository.save(new Product(ProductType.EXTRA_BED, "Extra säng", 200, 4));
            Product add2 = productRepository.save(new Product(ProductType.ROOM_SERVICE, "Room service", 75, null));

            Order b1 = new Order(LocalDate.now(), LocalDate.now().plusDays(1), 1, 2, "kund 1 anteckningar", new ArrayList<>());
            Order b2 = new Order(LocalDate.now(), LocalDate.now().plusDays(2), 2, 3, "kund 2 anteckningar", new ArrayList<>());

            LineItem badd1 = new LineItem(b1, add1, 2);
            LineItem badd2 = new LineItem(b1, add2, null);
            b1.getLineItems().add(badd1);
            b1.getLineItems().add(badd2);

            LineItem badd3 = new LineItem(b2, add1, 1);
            LineItem badd4 = new LineItem(b2, add2, null);
            b2.getLineItems().add(badd3);
            b2.getLineItems().add(badd4);

            orderRepository.save(b1);
            orderRepository.save(b2);
        };
    }
}
