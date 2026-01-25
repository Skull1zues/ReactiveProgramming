package com.soumya.sec10.assesment;

import com.soumya.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BufferAssignment {
    public static void main(String[] args) {
        var allowedCategory = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thiller"
        );

        orderStream()
                .filter( o->allowedCategory.contains(o.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(BufferAssignment::generate)
                .subscribe(Util.subscriber());

        Util.sleepSecond(60);
    }

    private static Flux<BookOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map( i ->BookOrder.create());
    }

    private static RevenueReport generate(List<BookOrder> orders){
        var ravenue = orders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre,
                        Collectors.summingInt(BookOrder::price)
                ));

        return new RevenueReport(LocalTime.now(), ravenue);
    }
}
