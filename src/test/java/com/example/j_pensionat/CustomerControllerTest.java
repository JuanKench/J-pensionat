package com.example.j_pensionat;

import com.example.j_pensionat.controller.CustomerController;
import com.example.j_pensionat.dto.CustomerDto;
import com.example.j_pensionat.service.CustomerServiceImpl;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerServiceImpl customerService;

    @Test
    public void testFindAllCustomers() throws Exception {
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
    public void testSaveCustomer() throws Exception {
        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers-new-form"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    public void testSaveCustomer_withNonLatinFirstName_returnValidError() throws Exception {    //Roliga tester mest
        mockMvc.perform(post("/customers/new").param("firstName", "ジェームズ"))
                .andExpect(status().isOk()).andExpect(view().name("customers-new-form"))
                .andExpect(model().attributeHasFieldErrors("customer", "firstName"));
    }

    @Test
    public void testSaveCustomer_withNonLatinLastName_returnValidError() throws Exception {
        mockMvc.perform(post("/customers/new").param("lastName", "サンダーランド"))
                .andExpect(status().isOk()).andExpect(view().name("customers-new-form"))
                .andExpect(model().attributeHasFieldErrors("customer", "lastName"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setId(1L);
        dto.setFirstName("Anna");
        dto.setLastName("Kumar");
        dto.setPhoneNumber("+6523897+");
        dto.setEmail("anna@gmail.com");
        dto.setAddress("yaba3");
        dto.isHasPaid();

        mockMvc.perform(put("/customers/edit/{id}", dto.getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // lurar html fixar så vi inte får errors och fyller ut models åt oss
                .param("id", String.valueOf(dto.getId()))
                .param("firstName", dto.getFirstName())
                .param("lastName", dto.getLastName())
                .param("phoneNumber", dto.getPhoneNumber())
                .param("email", dto.getEmail())
                .param("address", dto.getAddress())
                .param("hasPaid", String.valueOf(dto.isHasPaid())))
                .andExpect(status().is3xxRedirection()) // is3xxRedirection() kollar om servern säger åt client att gå till en annan url
                .andExpect(redirectedUrl("/customers"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));        // funkar för nu men ändras när service lagret blir uppdaterat
    }
}