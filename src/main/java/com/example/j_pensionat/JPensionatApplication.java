package com.example.j_pensionat;

import com.example.j_pensionat.enums.ProductType;
import com.example.j_pensionat.enums.RoomCategory;
import com.example.j_pensionat.model.*;
import com.example.j_pensionat.repository.*;
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
    public CommandLineRunner seed(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            LineItemRepository lineItemRepository,
            CustomerRepository customerRepository,
            RoomRepository roomRepository
    ) {
        return args -> {

            lineItemRepository.deleteAll();
            orderRepository.deleteAll();
            productRepository.deleteAll();
            customerRepository.deleteAll();
            roomRepository.deleteAll();

            Customer c1 = new Customer();
            c1.setFirstName("Anna");
            c1.setLastName("Svensson");
            c1.setEmail("anna@example.com");
            c1.setPhoneNumber("0701234567");
            c1.setAddress("Vägen 1");

            Customer c2 = new Customer();
            c2.setFirstName("Erik");
            c2.setLastName("Karlsson");
            c2.setEmail("erik@example.com");
            c2.setPhoneNumber("0707654321");
            c2.setAddress("Gatan 2");

            customerRepository.save(c1);
            customerRepository.save(c2);


            Room r1 = new Room();
            r1.setCategory(RoomCategory.SINGLE);
            r1.setDescription("Single room");
            r1.setName("Pineapple");
            r1.setSize(25);

            Room r2 = new Room();
            r2.setCategory(RoomCategory.DOUBLE);
            r2.setDescription("Double room");
            r2.setName("Strawberry");
            r2.setSize(50);

            roomRepository.save(r1);
            roomRepository.save(r2);


            Product add1 = productRepository.save(new Product(ProductType.EXTRA_BED, "Extra säng", 200, 4));
            Product add2 = productRepository.save(new Product(ProductType.ROOM_SERVICE, "Room service", 75, null));


            Order b1 = new Order(LocalDate.now(), LocalDate.now().plusDays(1), c1, r1, "kund 1 anteckningar", new ArrayList<>());
            Order b2 = new Order(LocalDate.now(), LocalDate.now().plusDays(2), c2, r2, "kund 2 anteckningar", new ArrayList<>());


            LineItem badd1 = new LineItem(b1, add1, 2);
            LineItem badd2 = new LineItem(b1, add2, 1);
            b1.getLineItems().add(badd1);
            b1.getLineItems().add(badd2);


            LineItem badd3 = new LineItem(b2, add1, 1);
            LineItem badd4 = new LineItem(b2, add2, 1);
            b2.getLineItems().add(badd3);
            b2.getLineItems().add(badd4);


            Customer c3 = new Customer();
            c3.setFirstName("Lisa");
            c3.setLastName("Lindgren");
            c3.setEmail("lisa@example.com");
            c3.setPhoneNumber("0709876543");
            c3.setAddress("Äppelvägen 3");
            customerRepository.save(c3);


            Room r3 = new Room();
            r3.setCategory(RoomCategory.DOUBLE);
            r3.setDescription("Large double room with sea view");
            r3.setName("Blueberry");
            r3.setSize(75);
            roomRepository.save(r3);


            Order b3 = new Order(LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), c3, r3, "kund 3 anteckningar", new ArrayList<>());


            LineItem badd5 = new LineItem(b3, add2, 1);
            b3.getLineItems().add(badd5);

            orderRepository.save(b3);


            // Save orders (cascades line items if properly configured)
            orderRepository.save(b1);
            orderRepository.save(b2);
        };
    }

}
