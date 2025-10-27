package com.soumya.sec01.subcriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

public class SubcriberImplemetation implements Subscriber<String> {
    private static final Logger logger = LoggerFactory.getLogger(SubcriberImplemetation.class);
    private Subscription subscription;


    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription=subscription;
    }

    @Override
    public void onNext(String email) {
        logger.info("received:- {}",email);

    }

    @Override
    public void onError(Throwable t) {
        logger.error("error:-",t);
    }

    @Override
    public void onComplete() {

    }
}
