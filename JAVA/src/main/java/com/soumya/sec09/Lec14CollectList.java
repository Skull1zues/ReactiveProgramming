package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.assesment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec14CollectList {
    public static final Logger log = LoggerFactory.getLogger(Lec14CollectList.class);

    record Car(String body, String engine, String tyres, Integer sheet){}
    public static void main(String[] args) {
        var client = new ExternalServiceClient();


        Flux.range(1,10)
                        .flatMap(client::getProduct)
                .collectList()
                                .subscribe(Util.subscriber("Sub1"));

        Util.sleepSecond(30);

    }


}
