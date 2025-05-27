package com.example.j_pensionat.controller;

import com.example.j_pensionat.enums.templatepath.CustomerTemplatePath;
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
        List<CustomerDto> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);
        return CustomerTemplatePath.LIST.getPath();
    }

    @GetMapping("/details/{id}")
    public String customerDetails(@PathVariable Long id, Model model){
        CustomerDto customerDto = customerService.findCustomerById(id);
        model.addAttribute("customer", customerDto);
        return CustomerTemplatePath.DETAILS.getPath();
    }

    @GetMapping("/new")
    public String createCustomerForm(Model model){
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customer", customerDto);
        return CustomerTemplatePath.CREATE.getPath();
    }

    @PostMapping("/new")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("customer", customerDto);
            return CustomerTemplatePath.CREATE.getPath();
        }
        customerService.saveCustomer(customerDto);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model){
        CustomerDto customerDto = customerService.findCustomerById(id);
        model.addAttribute("customer", customerDto);
        return CustomerTemplatePath.EDIT.getPath();
    }

    @PutMapping("/edit/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result) {
        if(result.hasErrors()){
            return CustomerTemplatePath.EDIT.getPath();
        }
        customerDto.setId(id);
        customerService.updateCustomer(customerDto);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomerViaPost(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }


}
