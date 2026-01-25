package com.soumya.sec02;

import com.soumya.common.Util;
import com.soumya.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {

    private static final Logger logger = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for(int i = 0;i<=5;i++) {
            var mono = client.getProductName(i);
            logger.info("getting response");
            mono.subscribe(Util.subscriber());
        }
        Util.sleepSecond(2);
    }

}
