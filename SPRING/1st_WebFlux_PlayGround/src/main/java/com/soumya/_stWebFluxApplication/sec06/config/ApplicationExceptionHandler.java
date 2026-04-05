package com.soumya._stWebFluxApplication.sec06.config;

import com.soumya._stWebFluxApplication.sec06.exception.CustomerNotFoundException;
import com.soumya._stWebFluxApplication.sec06.exception.InvalidInputException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
public class ApplicationExceptionHandler {


    public Mono<ServerResponse> customerNotFound(CustomerNotFoundException e, ServerRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problem.setType(URI.create("https://example.com/problrms/customer-not-found"));
        problem.setTitle("Customer Not Found");
        problem.setInstance(URI.create(request.path()));
        return ServerResponse.status(status).header("Content-Type","application/problem+json").body(Mono.just(problem), ProblemDetail.class);
    }


    public Mono<ServerResponse> invalidInput(InvalidInputException e, ServerRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problem.setType(URI.create("https://example.com/problrms/invalid-input"));
        problem.setTitle("Invalid Input");
        problem.setInstance(URI.create(request.path()));

        return ServerResponse.status(status).header("Content-Type","application/problem+json").body(Mono.just(problem), ProblemDetail.class);
    }
}
