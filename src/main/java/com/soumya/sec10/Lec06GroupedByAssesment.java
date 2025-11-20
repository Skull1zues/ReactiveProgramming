package com.soumya.sec10;

import com.soumya.common.Util;
import com.soumya.sec10.groupByAssesment.OrderProcessingService;
import com.soumya.sec10.groupByAssesment.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec06GroupedByAssesment {

    public static void main(String[] args) {
        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleepSecond(30);
    }




    private static Flux<PurchaseOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.create());
    }
}
