package com.soumya._stWebFluxApplication.sec09.repository;


import com.soumya._stWebFluxApplication.sec09.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {

}
