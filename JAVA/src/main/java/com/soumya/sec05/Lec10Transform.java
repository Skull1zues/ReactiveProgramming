package com.soumya.sec05;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class Lec10Transform {
    private static final Logger log  = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String name){}
    record PurchaseOrder(String productName, int price, int quantity){}


    public static void main(String[] args) {
        getCustomer()
                .doOnNext( i -> log.info("received {}",i))
                .doOnComplete(() -> log.info("Completed"))
                .doOnError(err -> log.error("error", err))
                .subscribe(Util.subscriber());

        getPurchaseOrder()
                .transform(addDebugger())
                //.doOnNext( i -> log.info("received {}",i))
                //.doOnComplete(() -> log.info("Completed"))
                //.doOnError(err -> log.error("error", err))
                .subscribe(Util.subscriber());

    }

    private static Flux<Customer> getCustomer(){
        return Flux.range(1,3)
                .map( i -> new Customer(i, Util.faker().name().firstName()));
    }


    private static Flux<PurchaseOrder> getPurchaseOrder(){
        return Flux.range(1,5)
                .map( i -> new PurchaseOrder(Util.faker().commerce().productName(), i,i*100));
    }

    private static <T>UnaryOperator<Flux<T>> addDebugger(){
        return flux -> flux
                    .doOnNext( i -> log.info("received {}",i))
                    .doOnComplete(() -> log.info("Completed"))
                    .doOnError(err -> log.error("error", err));
    }
}
