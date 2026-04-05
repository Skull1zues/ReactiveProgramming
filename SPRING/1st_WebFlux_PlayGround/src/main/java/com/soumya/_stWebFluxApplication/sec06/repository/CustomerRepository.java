package com.soumya._stWebFluxApplication.sec06.repository;

import com.soumya._stWebFluxApplication.sec06.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {
    Mono<Customer> findByName(String name);

    @Modifying
    @Query("DELETE FROM customer where id = :id")
    Mono<Boolean> deleteCustomerById(Integer id);

    Flux<Customer> findAllBy(Pageable pageable);

}
