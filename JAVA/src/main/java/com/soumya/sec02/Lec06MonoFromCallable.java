package com.soumya.sec02;

import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {
    private static final Logger logger = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3,4);
        Mono.fromCallable(() ->sum(list))
                .subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list){
        logger.info("findind the sum {}",list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
