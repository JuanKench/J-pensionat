package com.example.j_pensionat;


import com.example.j_pensionat.controller.CustomerController;
import com.example.j_pensionat.dto.CustomerDto;
import com.example.j_pensionat.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class TestCustomerController {

    private MockMvc mockMvc;
    private CustomerService customerService;
    private CustomerController controller;

    @BeforeEach
    void setup() {
        customerService = Mockito.mock(CustomerService.class);
        controller = new CustomerController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListCustomers() throws Exception {
        when(customerService.findAllCustomers()).thenReturn(List.of(new CustomerDto()));
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/customers-list"))
                .andExpect(model().attributeExists("customers"));
        verify(customerService).findAllCustomers();
    }

    @Test
    void testShowCustomerDetails() throws Exception {
        CustomerDto customer = new CustomerDto();
        customer.setId(1L);
        customer.setFirstName("Anna");

        when(customerService.findCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/customers/details/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/customers-details"))
                .andExpect(model().attributeExists("customer"));
    }
    @Test
    void testCreateCustomerForm() throws Exception {
        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/customers-new-form"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    void testDeleteCustomerViaPost() throws Exception {
        doNothing().when(customerService).deleteCustomerById(1L);
        mockMvc.perform(post("/customers/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));
        verify(customerService).deleteCustomerById(1L);
    }
}