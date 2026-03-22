package com.soumya._stWebFluxApplication.sec05.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.file.Watchable;

//@Service
public class WebFilterDemo1 implements WebFilter {
    private static final Logger logger = LoggerFactory.getLogger(WebFilterDemo1.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        logger.info("received");
        return chain.filter(exchange);
    }
}
