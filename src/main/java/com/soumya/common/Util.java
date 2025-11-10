package com.soumya.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Util {

    private static final Faker faker =Faker.instance();
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    public static <T> Subscriber<T> subscriber(){
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name){
        log.info(name);
        return new DefaultSubscriber<>(name);
    }

    public static void main(String[] args) {
        var mono = Mono.just(1);
        mono.subscribe(subscriber("sub1"));
        mono.subscribe(subscriber("sub2"));
    }

    public static Faker faker(){
        return faker;
    }

    public static void sleepSecond(int second) {
        try {
            Thread.sleep(Duration.ofSeconds(second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
