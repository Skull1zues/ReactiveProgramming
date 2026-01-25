package com.soumya.sec11.client;

public class ServerError extends RuntimeException{
    public ServerError() {
        super("Server Error");
    }
}
