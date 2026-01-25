package com.soumya.sec05.assesment;

import com.soumya.common.Util;

public class Assesment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for(int i=1;i<5;i++){
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSecond(20);
    }
}
