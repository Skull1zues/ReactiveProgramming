package com.soumya._stWebFluxApplication.tests.sec08;

import com.soumya._stWebFluxApplication.sec08.dto.ProductDto;
import com.soumya._stWebFluxApplication.sec08.dto.UploadResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductClient {
    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8080").build();

    public Mono<UploadResponse> uploadProduct(Flux<ProductDto> flux) {
        return this.client.post()
                .uri("/products/upload")
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(flux, ProductDto.class)
                .retrieve()
                .bodyToMono(UploadResponse.class);
    }

    public Flux<ProductDto> downloadProduct() {
        return this.client.get()
                .uri("/products/download")
                .accept(MediaType.APPLICATION_NDJSON)
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }
}

