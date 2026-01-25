package com.soumya.sec01.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class PublisherImpl implements Publisher {

    @Override
    public void subscribe(Subscriber s) {
        var subscription = new SubscriptionImpl(s);
        s.onSubscribe(subscription);
    }
}
