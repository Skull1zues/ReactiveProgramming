package com.soumya.sec02;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec07MonoFromRunable {

    private static final Logger logger = LoggerFactory.getLogger(Lec07MonoFromRunable.class);

    public static void main(String[] args) {
        getProducts(1).subscribe(Util.subscriber());
    }

    private static Mono<String> getProducts(int productId){
        if(productId == 1){
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId){
        logger.info("Notifying the business on unavaiable of product {}",productId);
    }
}
