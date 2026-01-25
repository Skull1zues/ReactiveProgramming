package com.soumya.sec03.assesment;

import com.soumya.common.Util;

public class Assignment {
    public static void main(String[] args) {
        var client = new ExternalStockServiceClient();
        var subscriber = new StockPriceObserver();
        client.getStock()
                .subscribe(subscriber);

        Util.sleepSecond(20);
    }
}
