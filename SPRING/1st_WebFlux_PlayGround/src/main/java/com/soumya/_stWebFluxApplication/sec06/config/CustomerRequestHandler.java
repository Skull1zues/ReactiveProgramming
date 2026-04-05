package com.soumya._stWebFluxApplication.sec06.config;


import com.soumya._stWebFluxApplication.sec06.validator.RequestValidator;
import com.soumya._stWebFluxApplication.sec06.dto.CustomerDto;
import com.soumya._stWebFluxApplication.sec06.entity.Customer;
import com.soumya._stWebFluxApplication.sec06.exception.ApplicationExceptions;
import com.soumya._stWebFluxApplication.sec06.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CustomerRequestHandler {

    @Autowired
    private CustomerService customerService;

    public Mono<ServerResponse> allCustomer(ServerRequest serverRequest){

        return this.customerService.getAllCustomers()
                        .as(flux -> ServerResponse.ok().body(flux, CustomerDto.class));

    }

    public Mono<ServerResponse> paginatedCustomer(ServerRequest serverRequest){
        var page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(1);
        var size = serverRequest.queryParam("size").map(Integer::parseInt).orElse(3);



        return this.customerService.getAllCustomers(page,size)
                .collectList()
                .flatMap(ServerResponse.ok()::bodyValue);

    }

    public Mono<ServerResponse> getCustomer(ServerRequest serverRequest){
        var id = Integer.parseInt(serverRequest.pathVariable("id"));


        return this.customerService.getCustomerById(id)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .flatMap(ServerResponse.ok()::bodyValue);

    }


    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest){
        return serverRequest.bodyToMono(CustomerDto.class)
                .transform(RequestValidator.validate())
                .as(this.customerService::saveCustomer)
                .flatMap(ServerResponse.ok()::bodyValue);


    }

    public Mono<ServerResponse> updateCustomer(ServerRequest serverRequest){
        var id = Integer.parseInt(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(CustomerDto.class)
                .transform(RequestValidator.validate())
                .as(validatedRequest -> this.customerService.updateCustomer(id, validatedRequest))
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .flatMap(ServerResponse.ok()::bodyValue);

    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest serverRequest){
        var id = Integer.parseInt(serverRequest.pathVariable("id"));


        return this.customerService.deleteCustomer(id)
                .filter(b -> b)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .then(ServerResponse.ok().build());

    }
}
