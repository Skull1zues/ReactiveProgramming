package com.soumya._stWebFluxApplication.sec09.service;

import com.github.javafaker.Faker;
import com.soumya._stWebFluxApplication.sec09.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class DataSetupService implements CommandLineRunner {
    @Autowired
    private ProductService productService;

    @Autowired
    private Faker faker;
    @Override
    public void run(String... args) throws Exception {
        Flux.range(1,1000)
                .delayElements(Duration.ofSeconds(1))
                .map(i->new ProductDto(null, faker.commerce().productName() , faker.number().randomDouble(0,100,100000)))
                .flatMap(dto -> this.productService.saveProducts(Mono.just(dto)))
                .subscribe();

    }
}
