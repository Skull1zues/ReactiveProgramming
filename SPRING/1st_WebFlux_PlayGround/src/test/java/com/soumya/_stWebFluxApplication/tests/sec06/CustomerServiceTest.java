package com.soumya._stWebFluxApplication.tests.sec06;


import com.soumya._stWebFluxApplication.sec04.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=sec06")
public class CustomerServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void allCustomers(){
        this.webTestClient.get()
                .uri("/customers")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerDto.class)
                .value(list -> logger.info("{}", list))
                .hasSize(10);
    }

    @Test
    public void paginatedAllCustomers(){
        this.webTestClient.get()
                .uri("/customers/paginated?page=3&size=2")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .consumeWith(r -> logger.info("{}", new String(r.getResponseBody())))
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(5)
                .jsonPath("$[1].id").isEqualTo(6);
    }

    @Test
    public void CustomersById(){
        this.webTestClient.get()
                .uri("/customers/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .consumeWith(r -> {
                    assert r.getResponseBody() != null;
                    logger.info("{}", new String(r.getResponseBody()));
                })
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("sam");
    }

    @Test
    public void CustomersByIdError(){
        this.webTestClient.get()
                .uri("/customers/11")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .expectBody()
                .consumeWith(r -> {
                    assert r.getResponseBody() != null;
                    logger.info("{}", new String(r.getResponseBody()));
                })
                .jsonPath("$.title").isEqualTo("Customer Not Found")
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.detail").isEqualTo("Customer [id=11] not Found");
    }

    @Test
    public void createCustomer(){
        this.webTestClient.post()
                .uri("/customers")
                .bodyValue(new CustomerDto(null,"Marshal","abc@gmail.com"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .consumeWith(r -> {
                    assert r.getResponseBody() != null;
                    logger.info("{}", new String(r.getResponseBody()));
                })
                .jsonPath("$.id").isEqualTo(11)
                .jsonPath("$.name").isEqualTo("Marshal");

        this.webTestClient.delete()
                .uri("/customers/11")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().isEmpty();
    }

    @Test
    public void InvalidCreateCustomer() {
        this.webTestClient.post()
                .uri("/customers")
                .bodyValue(new CustomerDto(null, "Marshal", "abcgmail.com"))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .consumeWith(r -> {
                    assert r.getResponseBody() != null;
                    logger.info("{}", new String(r.getResponseBody()));
                })
                .jsonPath("$.detail").isEqualTo("Valid EMAIL is required")
                .jsonPath("$.title").isEqualTo("Invalid Input");

    }

    @Test
    public void updateCustomer(){
        this.webTestClient.put()
                .uri("/customers/10")
                .bodyValue(new CustomerDto(null,"Noel","abc@gmail.com"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .consumeWith(r -> {
                    assert r.getResponseBody() != null;
                    logger.info("{}", new String(r.getResponseBody()));
                })
                .jsonPath("$.id").isEqualTo(10)
                .jsonPath("$.name").isEqualTo("Noel");

        this.webTestClient.put()
                .uri("/customers/10")
                .bodyValue(new CustomerDto(null,"Noel","abcgmail.com"))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .consumeWith(r -> {
                    assert r.getResponseBody() != null;
                    logger.info("{}", new String(r.getResponseBody()));
                })
                .jsonPath("$.detail").isEqualTo("Valid EMAIL is required")
                .jsonPath("$.title").isEqualTo("Invalid Input");
    }

    @Test
    public void updateCustomerException(){
        this.webTestClient.put()
                .uri("/customers/11")
                .bodyValue(new CustomerDto(null,"Noel","abc@gmail.com"))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .expectBody()
                .consumeWith(r -> {
                    assert r.getResponseBody() != null;
                    logger.info("{}", new String(r.getResponseBody()));
                })
                .jsonPath("$.title").isEqualTo("Customer Not Found")
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.detail").isEqualTo("Customer [id=11] not Found");
    }

    /*@Test
    public void CustomersByIdNotFound(){
        this.webTestClient.get()
                .uri("/customers/11")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody().isEmpty();

        this.webTestClient.delete()
                .uri("/customers/11")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody().isEmpty();

        this.webTestClient.put()
                .uri("/customers/11")
                .bodyValue(new CustomerDto(null,"ddd","gg"))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody().isEmpty();
    }*/

}
