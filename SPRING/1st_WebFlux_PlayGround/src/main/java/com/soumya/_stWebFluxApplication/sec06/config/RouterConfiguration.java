package com.soumya._stWebFluxApplication.sec06.config;


import com.soumya._stWebFluxApplication.sec06.exception.CustomerNotFoundException;
import com.soumya._stWebFluxApplication.sec06.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfiguration {

    @Autowired
    private CustomerRequestHandler customerRequestHandler;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @Bean
    public RouterFunction<ServerResponse> customerRoutes(){
        return RouterFunctions.route()
                .GET("/customers", customerRequestHandler::allCustomer)
                .GET("/customers/paginated", customerRequestHandler::paginatedCustomer)
                .GET("/customers/{id}", customerRequestHandler::getCustomer)
                .POST("/customers", customerRequestHandler::saveCustomer)
                .PUT("/customers/{id}", customerRequestHandler::updateCustomer)
                .DELETE("/customers/{id}", customerRequestHandler::deleteCustomer)
                .onError(CustomerNotFoundException.class,this.applicationExceptionHandler::customerNotFound)
                .onError(InvalidInputException.class,this.applicationExceptionHandler::invalidInput)
                .build();
    }
}
