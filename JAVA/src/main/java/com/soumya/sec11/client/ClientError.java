package com.soumya.sec11.client;

public class ClientError extends RuntimeException{

    public ClientError() {
        super("bad Request");
    }
}
