package com.soumya._stWebFluxApplication.sec08.controller;


import com.soumya._stWebFluxApplication.sec08.dto.ProductDto;
import com.soumya._stWebFluxApplication.sec08.dto.UploadResponse;
import com.soumya._stWebFluxApplication.sec08.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @PostMapping(value = "upload", consumes = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<UploadResponse> uploadProduct(@RequestBody Flux<ProductDto> productDtoFlux) {
        log.info("Invoke");
        return this.productService.saveProducts(productDtoFlux.doOnNext(productDto -> log.info(productDto.toString())))
                .then(productService.getProductsCount())
                .map(count -> new UploadResponse(UUID.randomUUID(), count));


    }

    @GetMapping(value = "download",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ProductDto> downloadProducts() {
        return this.productService.allProducts();
    }
}
