package com.soumya.sec09;

import com.soumya.common.Util;
import com.soumya.sec09.assesment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08ZipAssesment {
    public static final Logger log = LoggerFactory.getLogger(Lec08ZipAssesment.class);

    record Car(String body, String engine, String tyres, Integer sheet){}
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for(int i = 1;i<10; i++){
            client.getProduct(i)
                    .subscribe(Util.subscriber("sub"+i));
        }

        Util.sleepSecond(4);

    }


}
