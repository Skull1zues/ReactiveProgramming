package com.soumya.sec02;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec10MonoToDefer {

    private static final Logger logger = LoggerFactory.getLogger(Lec10MonoToDefer.class);



    public static void main(String[] args) {
        //createPublisher();
        //.subscribe(Util.subscriber());
        var mono=Mono.defer(Lec10MonoToDefer::createPublisher);
        logger.info("created Mono");
        mono.subscribe(Util.subscriber());

    }

    private static Mono<Integer> createPublisher(){
        var list = List.of(1,2,3,4);
        Util.sleepSecond(2);
        return Mono.fromSupplier(
                () ->sum(list)
        );
    }

    private static int sum(List<Integer> list) {
        logger.info("findind the sum {}",list);
        Util.sleepSecond(5);
        return list.stream().mapToInt(a -> a).sum();
    }

}
