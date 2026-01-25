package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.assesment.ExternalServiceClient;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec15Then {
    public static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {


        var records = List.of("a","b","c");
        saveRecord(records)
                .then(sendNotification(records))
                .subscribe(Util.subscriber());



        Util.sleepSecond(30);

    }
    private static Flux<String> saveRecord(List<String> record){
        return Flux.fromIterable(record)
                .map(r -> "Saved "+ r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records){
        return Mono.fromRunnable(() -> log.info("all these {} are saved successfully",records));
    }


}
