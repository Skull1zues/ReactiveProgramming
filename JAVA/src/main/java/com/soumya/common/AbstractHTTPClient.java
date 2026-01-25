package com.soumya.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHTTPClient {
    public static final String BASE_URL = "http://localhost:7070";

    protected final HttpClient httpClient;

    public AbstractHTTPClient() {
        var  loopResources = LoopResources.create("vins",1,true);
        this.httpClient = HttpClient.create().runOn(loopResources).baseUrl(BASE_URL);
    }
}
