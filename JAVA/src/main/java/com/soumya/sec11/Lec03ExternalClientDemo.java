package com.soumya.sec11;

import com.soumya.common.Util;
import com.soumya.sec11.client.ExternalServiceClient;
import com.soumya.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalClientDemo {
    private static Logger log = LoggerFactory.getLogger(Lec03ExternalClientDemo.class);
    public static void main(String[] args) {
        Flux.just("a")
                        .retry(1)
                                .retry(2)
                                        .subscribe(Util.subscriber());
        Util.sleepSecond(60);
    }

    private static void repeat(){
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry(){
        var client = new ExternalServiceClient();
        client.getProductName(1)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError(){
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("retring {}",rs.failure().getMessage()));
    }
}
