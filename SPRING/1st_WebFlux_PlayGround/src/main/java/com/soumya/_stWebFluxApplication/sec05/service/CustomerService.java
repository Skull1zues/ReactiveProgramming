package com.soumya._stWebFluxApplication.sec05.service;


import com.soumya._stWebFluxApplication.sec05.dto.CustomerDto;
import com.soumya._stWebFluxApplication.sec05.mapper.EntityDtoMapper;
import com.soumya._stWebFluxApplication.sec05.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Flux<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .map(EntityDtoMapper::toDto);
    }

    public Mono<CustomerDto> getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .map(EntityDtoMapper::toDto);
    }

    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> mono) {
        return mono.map(EntityDtoMapper::toEntity) //input validation need to do
                .flatMap(entity -> this.customerRepository.save(entity))
                .map(EntityDtoMapper::toDto);
    }

    public Mono<CustomerDto> updateCustomer(Integer id, Mono<CustomerDto> mono) {
        return this.customerRepository.findById(id)
                .flatMap(entity -> mono)
                .map(EntityDtoMapper::toEntity)
                .doOnNext(c -> c.setId(id))
                .flatMap(entity -> this.customerRepository.save(entity))
                .map(EntityDtoMapper::toDto);
    }

    public Mono<Boolean> deleteCustomer(Integer id) {
        return this.customerRepository.deleteCustomerById(id);

    }

    public Flux<CustomerDto> getAllCustomers(Integer page, Integer size) {
        return customerRepository.findAllBy(PageRequest.of(page -1,size))
                .map(EntityDtoMapper::toDto);
    }

}