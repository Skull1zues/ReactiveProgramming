package com.soumya._stWebFluxApplication.sec02.repository;

import com.soumya._stWebFluxApplication.sec01.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {
    Flux<Product> findByPriceBetween(int priceStart, int priceEnd);

    Flux<Product> findAllBy(Pageable pageable);
}
