package com.soumya.sec10.groupByAssesment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class OrderProcessingService {

    private static final Map<String,UnaryOperator<Flux<PurchaseOrder>>> PROCESSER_MAP = Map.of(
            "Kids", kidsProcessing(),
            "Automotive", automotiveProcessing()
    );


    private static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing(){
        return flux -> flux
                .map(po -> new PurchaseOrder(po.item(), po.category(), po.price() +100));
    }
    private static UnaryOperator<Flux<PurchaseOrder>> kidsProcessing(){
        return flux -> flux
                .flatMap(po -> getKidsOrderProcessing(po).flux().startWith(po));
    }

    private static Mono<PurchaseOrder> getKidsOrderProcessing(PurchaseOrder order){
        return Mono.fromSupplier(() -> new PurchaseOrder(
                order.item() +"FREE",
                order.category(),
                0
        ))    ;
    }

    public static Predicate<PurchaseOrder> canProcess(){
        return po -> PROCESSER_MAP.containsKey(po.category());
    }

    public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category){
        return PROCESSER_MAP.get(category);
    }


}
