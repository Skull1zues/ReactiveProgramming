package com.soumya._stWebFluxApplication.sec08.repository;


import com.soumya._stWebFluxApplication.sec08.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<com.soumya._stWebFluxApplication.sec08.entity.Product,Integer> {

}
