package com.soumya.sec07;

import com.soumya.common.Util;
import com.soumya.sec07.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec08Parallel {

    public static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);
    public static void main(String[] args) {
        Flux.range(1,25)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .map(Lec08Parallel::process)
                .sequential()
                .subscribe(Util.subscriber());


        Util.sleepSecond(9);
    }


    private static String process(int input){
        Util.sleepSecond(1);
        return input + "-processed";
    }
}
