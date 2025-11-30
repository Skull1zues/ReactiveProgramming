package com.soumya.Sec13;

import com.soumya.Sec13.client.ExternalServiceClient;
import com.soumya.common.Util;
import reactor.util.context.Context;

public class Lec04ContextRateLimiterDemo {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for(int i=0;i<20;i++){
            client.getBook()
                    .contextWrite(Context.of("user","user3"))
                    .subscribe(Util.subscriber());
        }

        Util.sleepSecond(20);
    }
}
