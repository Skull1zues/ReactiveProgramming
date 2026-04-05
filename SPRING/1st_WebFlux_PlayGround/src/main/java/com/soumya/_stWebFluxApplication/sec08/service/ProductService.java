package com.soumya._stWebFluxApplication.sec08.service;


import com.soumya._stWebFluxApplication.sec08.dto.ProductDto;
import com.soumya._stWebFluxApplication.sec08.mapper.ProductMapper;
import com.soumya._stWebFluxApplication.sec08.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> saveProducts(Flux<ProductDto> productDto) {
        return productDto.map(ProductMapper::mapProductDtoToProduct)
                .as(productRepository::saveAll)
                .map(ProductMapper::mapProductToProductDto);


    }

    public Mono<Long> getProductsCount() {
        return productRepository.count();
    }

    public Flux<ProductDto> allProducts() {
        return productRepository.findAll()
                .map(ProductMapper::mapProductToProductDto);
    }
}
