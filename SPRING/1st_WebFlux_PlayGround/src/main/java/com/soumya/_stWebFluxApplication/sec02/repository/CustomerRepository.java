package com.soumya._stWebFluxApplication.sec02.repository;

import com.soumya._stWebFluxApplication.sec02.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {
    Mono<Customer> findByName(String name);

}
