package com.example.j_pensionat.mapper;

import org.mapstruct.Mapper;
import com.example.j_pensionat.model.Customer;
import com.example.j_pensionat.dto.CustomerDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto dto);
}