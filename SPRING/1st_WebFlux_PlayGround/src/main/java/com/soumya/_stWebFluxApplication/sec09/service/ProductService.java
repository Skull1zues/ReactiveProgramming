package com.soumya._stWebFluxApplication.sec09.service;


import com.soumya._stWebFluxApplication.sec09.dto.ProductDto;
import com.soumya._stWebFluxApplication.sec09.mapper.ProductMapper;
import com.soumya._stWebFluxApplication.sec09.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Sinks.Many<ProductDto> sink;

    public Mono<ProductDto> saveProducts(Mono<ProductDto> productDto) {
        return productDto.map(ProductMapper::mapProductDtoToProduct)
                .flatMap(productRepository::save)
                .map(ProductMapper::mapProductToProductDto)
                .doOnNext(dto -> this.sink.tryEmitNext(dto));


    }
    public Flux<ProductDto> productStream(){
        return this.sink.asFlux();
    }

}
