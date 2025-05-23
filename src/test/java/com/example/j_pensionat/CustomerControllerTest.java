package com.example.j_pensionat;

import com.example.j_pensionat.controller.CustomerController;
import com.example.j_pensionat.dto.CustomerDto;
import com.example.j_pensionat.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerServiceImpl customerService;

    @Test
    public void getAllCustomers_shouldReturnCustomerListView() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setId(1L);
        dto.setFirstName("Anna");

        when(customerService.findAllCustomers()).thenReturn(List.of(dto));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers-list"))
                .andExpect(model().attributeExists("customers"));
    }

    @Test
    public void getNewCustomerForm_shouldReturnFormView() throws Exception {
        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers-new-form"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test

}