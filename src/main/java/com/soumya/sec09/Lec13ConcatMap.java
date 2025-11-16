package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.assesment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec13ConcatMap {
    public static final Logger log = LoggerFactory.getLogger(Lec13ConcatMap.class);

    record Car(String body, String engine, String tyres, Integer sheet){}
    public static void main(String[] args) {
        var client = new ExternalServiceClient();


        Flux.range(1,10)
                        .concatMap(client::getProduct)
                                .subscribe(Util.subscriber("Sub1"));

        Util.sleepSecond(30);

    }


}
