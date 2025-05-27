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
            // Clear database
            lineItemRepository.deleteAll();
            orderRepository.deleteAll();
            productRepository.deleteAll();
            customerRepository.deleteAll();
            roomRepository.deleteAll();

            // --- Rooms ---
            Room r1 = new Room();
            r1.setCategory(RoomCategory.SINGLE);
            r1.setDescription("Enkelrum med lugn utsikt mot innergården.");
            r1.setName("Pineapple");
            r1.setSize(25);
            r1.setMaxGuests(2);
            roomRepository.save(r1);

            Room r2 = new Room();
            r2.setCategory(RoomCategory.DOUBLE);
            r2.setDescription("Dubbelrum med romantisk balkong och kvällssol.");
            r2.setName("Strawberry");
            r2.setSize(50);
            r2.setMaxGuests(4);
            roomRepository.save(r2);

            Room r3 = new Room();
            r3.setCategory(RoomCategory.DOUBLE);
            r3.setDescription("Stort dubbelrum med havsutsikt och sittgrupp.");
            r3.setName("Blueberry");
            r3.setSize(75);
            r3.setMaxGuests(6);
            roomRepository.save(r3);

            Room r4 = new Room();
            r4.setCategory(RoomCategory.SINGLE);
            r4.setDescription("Charmigt enkelrum med färgglad inredning.");
            r4.setName("Orange");
            r4.setSize(25);
            r4.setMaxGuests(2);
            roomRepository.save(r4);

            Room r5 = new Room();
            r5.setCategory(RoomCategory.SINGLE);
            r5.setDescription("Ljust enkelrum med utsikt mot trädgården.");
            r5.setName("Apple");
            r5.setSize(25);
            r5.setMaxGuests(2);
            roomRepository.save(r5);

            // --- Customers ---
            Customer c1 = new Customer();
            c1.setFirstName("Anna");
            c1.setLastName("Svensson");
            c1.setEmail("anna@example.com");
            c1.setPhoneNumber("0701234567");
            c1.setAddress("Vägen 1");
            c1.setHasPaid(false);
            customerRepository.save(c1);

            Customer c2 = new Customer();
            c2.setFirstName("Erik");
            c2.setLastName("Karlsson");
            c2.setEmail("erik@example.com");
            c2.setPhoneNumber("0707654321");
            c2.setAddress("Gatan 2");
            c2.setHasPaid(false);
            customerRepository.save(c2);

            Customer c3 = new Customer();
            c3.setFirstName("Lisa");
            c3.setLastName("Lindgren");
            c3.setEmail("lisa@example.com");
            c3.setPhoneNumber("0709876543");
            c3.setAddress("Äppelvägen 3");
            c3.setHasPaid(false);
            customerRepository.save(c3);

            // --- Products ---
            Product add1 = new Product(ProductType.EXTRA_BED, "Extra säng", 200, 4);
            Product add2 = new Product(ProductType.ROOM_SERVICE, "Room service", 75, null);
            productRepository.save(add1);
            productRepository.save(add2);

            // --- Orders ---
            Order b1 = new Order(LocalDate.now(), LocalDate.now().plusDays(1), c1, r1, "kund 1 anteckningar", new ArrayList<>());
            b1.getLineItems().add(new LineItem(b1, add1, 0));
            b1.getLineItems().add(new LineItem(b1, add2, 1));
            orderRepository.save(b1);

            Order b2 = new Order(LocalDate.now(), LocalDate.now().plusDays(2), c2, r2, "kund 2 anteckningar", new ArrayList<>());
            b2.getLineItems().add(new LineItem(b2, add1, 1));
            b2.getLineItems().add(new LineItem(b2, add2, 1));
            orderRepository.save(b2);

            Order b3 = new Order(LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), c3, r3, "kund 3 anteckningar", new ArrayList<>());
            b3.getLineItems().add(new LineItem(b3, add2, 1));
            orderRepository.save(b3);
        };
    }



//    @Bean
//    public CommandLineRunner seed(
//            OrderRepository orderRepository,
//            ProductRepository productRepository,
//            LineItemRepository lineItemRepository,
//            CustomerRepository customerRepository,
//            RoomRepository roomRepository
//    ) {
//        return args -> {
//            // Rensa databasen
//            lineItemRepository.deleteAll();
//            orderRepository.deleteAll();
//            productRepository.deleteAll();
//            customerRepository.deleteAll();
//            roomRepository.deleteAll();
//
//            // --- Rum ---
//            Room r1 = new Room();
//            r1.setCategory(RoomCategory.SINGLE);
//            r1.setDescription("Enkelrum med lugn utsikt mot innergården.");
//            r1.setName("Pineapple");
//            r1.setSize(25);
//            r1.setMaxGuests(2);
//            roomRepository.save(r1);
//
//            Room r2 = new Room();
//            r2.setCategory(RoomCategory.DOUBLE);
//            r2.setDescription("Dubbelrum med romantisk balkong och kvällssol.");
//            r2.setName("Strawberry");
//            r2.setSize(50);
//            r2.setMaxGuests(4);
//            roomRepository.save(r2);
//
//            Room r3 = new Room();
//            r3.setCategory(RoomCategory.DOUBLE);
//            r3.setDescription("Stort dubbelrum med havsutsikt och sittgrupp.");
//            r3.setName("Blueberry");
//            r3.setSize(75);
//            r3.setMaxGuests(6);
//            roomRepository.save(r3);
//
//            Room r4 = new Room();
//            r4.setCategory(RoomCategory.SINGLE);
//            r4.setDescription("Charmigt enkelrum med färgglad inredning.");
//            r4.setName("Orange");
//            r4.setSize(25);
//            r4.setMaxGuests(2);
//            roomRepository.save(r4);
//
//            Room r5 = new Room();
//            r5.setCategory(RoomCategory.SINGLE);
//            r5.setDescription("Ljust enkelrum med utsikt mot trädgården.");
//            r5.setName("Apple");
//            r5.setSize(25);
//            r5.setMaxGuests(2);
//            roomRepository.save(r5);
//
//            // --- Kunder ---
//            Customer c1 = new Customer();
//            c1.setFirstName("Anna");
//            c1.setLastName("Svensson");
//            c1.setEmail("anna@example.com");
//            c1.setPhoneNumber("0701234567");
//            c1.setAddress("Vägen 1");
//            customerRepository.save(c1);
//
//            Customer c2 = new Customer();
//            c2.setFirstName("Erik");
//            c2.setLastName("Karlsson");
//            c2.setEmail("erik@example.com");
//            c2.setPhoneNumber("0707654321");
//            c2.setAddress("Gatan 2");
//            customerRepository.save(c2);
//
//            Customer c3 = new Customer();
//            c3.setFirstName("Lisa");
//            c3.setLastName("Lindgren");
//            c3.setEmail("lisa@example.com");
//            c3.setPhoneNumber("0709876543");
//            c3.setAddress("Äppelvägen 3");
//            customerRepository.save(c3);
//
//            // --- Produkter ---
//            Product add1 = productRepository.save(new Product(ProductType.EXTRA_BED, "Extra säng", 200, 4));
//            Product add2 = productRepository.save(new Product(ProductType.ROOM_SERVICE, "Room service", 75, null));
//
//            // --- Bokningar ---
//            Order b1 = new Order(LocalDate.now(), LocalDate.now().plusDays(1), c1, r1, "kund 1 anteckningar", new ArrayList<>());
//            b1.getLineItems().add(new LineItem(b1, add1, 0));
//            b1.getLineItems().add(new LineItem(b1, add2, 1));
//            orderRepository.save(b1);
//
//            Order b2 = new Order(LocalDate.now(), LocalDate.now().plusDays(2), c2, r2, "kund 2 anteckningar", new ArrayList<>());
//            b2.getLineItems().add(new LineItem(b2, add1, 1));
//            b2.getLineItems().add(new LineItem(b2, add2, 1));
//            orderRepository.save(b2);
//
//            Order b3 = new Order(LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), c3, r3, "kund 3 anteckningar", new ArrayList<>());
//            b3.getLineItems().add(new LineItem(b3, add2, 1));
//            orderRepository.save(b3);
//        };
//    }
}
