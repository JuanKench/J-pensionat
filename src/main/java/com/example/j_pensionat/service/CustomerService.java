package com.example.j_pensionat.service;

import com.example.j_pensionat.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAllCustomers();
    CustomerDto findCustomerById(Long id);
    void saveCustomer(CustomerDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteCustomerById(Long id);
}