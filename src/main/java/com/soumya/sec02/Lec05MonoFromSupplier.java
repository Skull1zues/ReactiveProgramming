package com.soumya.sec02;

import com.soumya.common.DefaultSubscriber;
import com.soumya.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {
    private static final Logger logger = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3,4);
        Mono.fromSupplier(() ->sum(list))
                .subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list){
        logger.info("findind the sum {}",list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
