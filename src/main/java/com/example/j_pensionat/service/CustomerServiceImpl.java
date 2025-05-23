package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.CustomerDto;
import com.example.j_pensionat.mapper.CustomerMapper;
import com.example.j_pensionat.model.Customer;
import com.example.j_pensionat.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
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
