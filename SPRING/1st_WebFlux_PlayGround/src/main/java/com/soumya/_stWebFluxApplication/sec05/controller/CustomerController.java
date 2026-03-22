package com.soumya._stWebFluxApplication.sec05.controller;


import com.soumya._stWebFluxApplication.sec05.dto.CustomerDto;
import com.soumya._stWebFluxApplication.sec05.exception.ApplicationExceptions;
import com.soumya._stWebFluxApplication.sec05.filter.Category;
import com.soumya._stWebFluxApplication.sec05.service.CustomerService;
import com.soumya._stWebFluxApplication.sec05.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> getAllCustomers(@RequestAttribute("category")Category category) {
        System.out.println("category = " + category);
        return this.customerService.getAllCustomers();
    }

    @GetMapping("paginated")
    public Mono<List<CustomerDto>> getAllCustomersPagable(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer size) {
        return this.customerService.getAllCustomers(page,size)
                .collectList();
    }

    @GetMapping("{id}")
    public Mono<CustomerDto> getCustomerById(@PathVariable Integer id) {
        return this.customerService.getCustomerById(id)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id));
    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> customerDto) {
/*        var validatedMono = customerDto.transform(RequestValidator.validate());
        return this.customerService.saveCustomer(validatedMono);*/

        return customerDto.transform(RequestValidator.validate())
                .as(this.customerService::saveCustomer);
    }

    @PutMapping("{id}")
    public Mono<CustomerDto> updateCustomer(@RequestBody Mono<CustomerDto> customerDto, @PathVariable Integer id) {

        return customerDto.transform(RequestValidator.validate())
                .as(validReq -> this.customerService.updateCustomer(id, validReq))
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id));
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id) {
        return this.customerService.deleteCustomer(id)
                .filter(b -> b)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id)).then();
    }
}
