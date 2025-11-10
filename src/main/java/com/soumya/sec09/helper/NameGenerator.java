package com.soumya.sec09.helper;

import com.soumya.sec09.Lec01StartsWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class NameGenerator {
    public static final Logger log = LoggerFactory.getLogger(NameGenerator.class);

    public Flux<String> generateNames(){
        Flux.generate()
    }

}
