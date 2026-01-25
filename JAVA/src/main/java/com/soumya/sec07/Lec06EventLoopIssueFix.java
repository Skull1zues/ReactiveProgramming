package com.soumya.sec07;

import com.soumya.common.Util;
import com.soumya.sec07.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06EventLoopIssueFix {

    public static final Logger log = LoggerFactory.getLogger(Lec06EventLoopIssueFix.class);
    public static void main(String[] args) {
        var c = System.currentTimeMillis();
        var client = new ExternalServiceClient();

        for(int i = 0;i<=5;i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());

        }
        System.out.println("time= "+(c-System.currentTimeMillis()));
        Util.sleepSecond(20);
    }

    private static String process(String input){
        Util.sleepSecond(1);
        return input + "-processed";
    }
}
