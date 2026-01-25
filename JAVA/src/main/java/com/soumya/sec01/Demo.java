package com.soumya.sec01;

import com.soumya.sec01.publisher.PublisherImpl;
import com.soumya.sec01.publisher.SubscriptionImpl;
import com.soumya.sec01.subcriber.SubcriberImplemetation;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        demo3();
    }

    private static void demo1(){
        var publisher = new PublisherImpl();
        var subscriber = new SubcriberImplemetation();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubcriberImplemetation();
        publisher.subscribe(subscriber);

            subscriber.getSubscription().request(3);
            Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

    }
    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubcriberImplemetation();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        //subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

    }
}
