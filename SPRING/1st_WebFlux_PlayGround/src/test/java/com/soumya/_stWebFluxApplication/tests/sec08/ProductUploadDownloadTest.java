package com.soumya._stWebFluxApplication.tests.sec08;

import com.soumya._stWebFluxApplication.sec08.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.nio.file.Path;
import java.time.Duration;

public class ProductUploadDownloadTest {

    private static final Logger log = LoggerFactory.getLogger(ProductUploadDownloadTest.class);
    private final ProductClient productClient = new ProductClient();
    @Test
    public void testProductUpload() {

        var flux = Flux.range(1,100000)
                .map(i ->new ProductDto(null, "iphone"+i,1000.00*i))
                ;
        this.productClient.uploadProduct(flux)
                .doOnNext(r -> log.info(r.toString()))
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    public void download(){
        this.productClient.downloadProduct()
                .map(ProductDto::toString)
                .doOnNext(log::info)
                .as(flux -> FileWriter.create(flux, Path.of("products.txt")))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
