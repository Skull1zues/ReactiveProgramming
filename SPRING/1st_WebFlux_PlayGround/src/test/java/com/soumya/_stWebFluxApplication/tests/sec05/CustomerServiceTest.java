package com.soumya._stWebFluxApplication.tests.sec05;


import com.soumya._stWebFluxApplication.sec05.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=sec05")
public class CustomerServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @Autowired
    private WebTestClient webTestClient;

    public void unauthorized(){
        this.webTestClient.get()
                .uri("/customers")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED);

        this.validGet("secret", HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void standardCategory(){
        this.validGet("secret123", HttpStatus.OK);
        this.validPost("secret123", HttpStatus.FORBIDDEN);
    }

    @Test
    public void primeCategory(){
        this.validGet("secret456", HttpStatus.OK);
        this.validPost("secret456", HttpStatus.OK);
    }

    private void validGet(String token, HttpStatus expectedStatus) {
        this.webTestClient.get()
                .uri("/customers")
                .header("auth-token",token)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus);
    }

    private void validPost(String token, HttpStatus expectedStatus) {
        var dto = new CustomerDto(null, "Soumya", "abc@gmail.com");
        this.webTestClient.post()
                .uri("/customers")
                .bodyValue(dto)
                .header("auth-token",token)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus);
    }



}
