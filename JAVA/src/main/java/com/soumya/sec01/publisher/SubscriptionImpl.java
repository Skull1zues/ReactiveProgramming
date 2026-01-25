package com.soumya.sec01.publisher;

import com.github.javafaker.Faker;
import com.soumya.sec01.subcriber.SubcriberImplemetation;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS =10;

    private final Faker faker;

    private final Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long n) {
        if(isCancelled){
            return;
        }
        logger.info("Subscriber has requested {} items",n);
        if(n>MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("Validation Error"));
            this.isCancelled=true;
            return;
        }
        for (int i = 0;i<n && count <MAX_ITEMS;i++){
            this.subscriber.onNext(this.faker.internet().emailAddress());
            count++;
        }

        if(count == MAX_ITEMS){
            logger.info("no more data to produce");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }

    }

    @Override
    public void cancel() {
        logger.info("Subscription got canceled");
        isCancelled =true;

    }
}
