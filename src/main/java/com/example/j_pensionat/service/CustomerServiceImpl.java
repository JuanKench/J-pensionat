package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.CustomerDto;
import com.example.j_pensionat.mapper.CustomerMapper;
import com.example.j_pensionat.model.Customer;
import com.example.j_pensionat.repository.CustomerRepository;
import com.example.j_pensionat.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> {
                    CustomerDto dto = new CustomerDto();
                    dto.setId(customer.getId());
                    dto.setFirstName(customer.getFirstName());
                    dto.setLastName(customer.getLastName());

                    boolean hasBookings = !orderRepository.findByCustomerId(customer.getId()).isEmpty();
                    dto.setHasBookings(hasBookings);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<CustomerDto> customerDtos() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kund kunde inte hittas"));
        return customerMapper.toDto(customer);
    }

    @Override
    public void saveCustomer(CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

}