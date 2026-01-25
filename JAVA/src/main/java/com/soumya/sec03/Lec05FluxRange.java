package com.soumya.sec03;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Random;

public class Lec05FluxRange {
    private static final Logger logger = LoggerFactory.getLogger(Lec05FluxRange.class);

    public static void main(String[] args) {
        Random random = new Random();
        Flux.range(1,10).map(i->random.nextInt(1,1000))
                .subscribe(Util.subscriber());
        var current = System.currentTimeMillis();
        Flux.range(1,10000).map(i-> Util.faker().name().firstName())
                .subscribe(Util.subscriber());
        var du1 = System.currentTimeMillis()-current;
        current = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            logger.info(Util.faker().name().firstName());
        }
        var du2 = System.currentTimeMillis() - current;

        logger.info("1:- {} ; 2:- {}",du1,du2);


    }
}
