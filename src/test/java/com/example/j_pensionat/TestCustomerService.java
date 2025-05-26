package com.example.j_pensionat;

import com.example.j_pensionat.dto.CustomerDto;
import com.example.j_pensionat.mapper.CustomerMapper;
import com.example.j_pensionat.model.Customer;
import com.example.j_pensionat.model.Order;
import com.example.j_pensionat.repository.CustomerRepository;
import com.example.j_pensionat.repository.OrderRepository;

import com.example.j_pensionat.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TestCustomerService {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private CustomerMapper customerMapper;
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        orderRepository = mock(OrderRepository.class);
        customerMapper = mock(CustomerMapper.class);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper, orderRepository);
    }

    @Test
    void testFindAllCustomers_WithAndWithoutBookings() {        //Att kunder mappas korrekt och flaggan hasBookings sätts beroende på om bokningar finns
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Anna");
        customer.setLastName("Andersson");

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(orderRepository.findByCustomerId(1L)).thenReturn(List.of(new Order())); // has bookings

        List<CustomerDto> result = customerService.findAllCustomers();

        assertEquals(1, result.size());
        CustomerDto dto = result.get(0);
        assertEquals(1L, dto.getId());
        assertEquals("Anna", dto.getFirstName());
        assertEquals("Andersson", dto.getLastName());
        assertTrue(dto.isHasBookings());

        verify(customerRepository).findAll();
        verify(orderRepository).findByCustomerId(1L);
    }

    @Test
    void testFindCustomerById_Success() {       //Hämtning av kund och korrekt mapping till DTO
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Erik");
        customer.setLastName("Eriksson");

        CustomerDto dto = new CustomerDto();
        dto.setId(2L);
        dto.setFirstName("Erik");
        dto.setLastName("Eriksson");

        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(dto);

        CustomerDto result = customerService.findCustomerById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Erik", result.getFirstName());

        verify(customerRepository).findById(2L);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testFindCustomerById_NotFound() {          //Undantag kastas om kund inte finns
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.findCustomerById(999L);
        });

        assertEquals("Kund kunde inte hittas", exception.getMessage());
        verify(customerRepository).findById(999L);
        verifyNoInteractions(customerMapper);
    }

    @Test
    void testSaveCustomer() {       //  DTO → Entity konvertering och sparande
        CustomerDto dto = new CustomerDto();
        dto.setFirstName("Lisa");

        Customer customer = new Customer();
        customer.setFirstName("Lisa");

        when(customerMapper.toEntity(dto)).thenReturn(customer);
        customerService.saveCustomer(dto);

        verify(customerMapper).toEntity(dto);
        verify(customerRepository).save(customer);
    }

    @Test
    void testUpdateCustomer() {         //	gör samma som save, men används för uppdatering
        CustomerDto dto = new CustomerDto();
        dto.setId(3L);
        dto.setFirstName("Maria");

        Customer customer = new Customer();
        customer.setId(3L);
        customer.setFirstName("Maria");

        when(customerMapper.toEntity(dto)).thenReturn(customer);

        customerService.updateCustomer(dto);

        verify(customerMapper).toEntity(dto);
        verify(customerRepository).save(customer);
    }

    @Test
    void testDeleteCustomerById() {    // kollar att rätt ID tas bort
        Long id = 5L;
        customerService.deleteCustomerById(id);
        verify(customerRepository).deleteById(id);
    }

}
