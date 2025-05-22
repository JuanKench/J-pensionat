package com.example.j_pensionat.controller;

import org.springframework.ui.Model;
import com.example.j_pensionat.dto.CustomerDto;
import com.example.j_pensionat.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model){
        List<CustomerDto> customers = this.customerService.findAllCustomers();
        model.addAttribute("customers", customers);
        return "customers-list";
    }

    @GetMapping("/details/{id}")
    public String customerDetails(@PathVariable Long id, Model model){
        CustomerDto customerDto = this.customerService.findCustomerById(id);
        model.addAttribute("customer", customerDto);
        return "customers-details";
    }

    @GetMapping("/new")
    public String createCustomerForm(Model model){
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customer", customerDto);
        return "customers-new-form";
    }

    @PostMapping("/new")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("customer", customerDto);
            return "customers-new-form";
        }
        this.customerService.saveCustomer(customerDto);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model){
        CustomerDto customerDto = this.customerService.findCustomerById(id);
        model.addAttribute("customer", customerDto);
        return "customers-edit-form";
    }

    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result) {
        if(result.hasErrors()){
            return "customers-edit-form";
        }
        customerDto.setId(id);
        this.customerService.updateCustomer(customerDto);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }

}
