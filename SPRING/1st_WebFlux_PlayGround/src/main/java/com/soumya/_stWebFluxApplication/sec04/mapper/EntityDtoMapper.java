package com.soumya._stWebFluxApplication.sec04.mapper;

import com.soumya._stWebFluxApplication.sec04.entity.Customer;
import com.soumya._stWebFluxApplication.sec04.dto.CustomerDto;

public class EntityDtoMapper {
    public static Customer toEntity(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setId(customerDto.id());
        return customer;
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail());
    }


}
