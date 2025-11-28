package com.soumya.sec12.assesment;

public record SlackMessage(String sender,
                           String message) {

    private static final String MESSAGE_FORMAT = "[%S -> %s] : %s";

    public String getMessageFormat(String receiver) {
        return MESSAGE_FORMAT.formatted(sender,receiver,message);
    }
}
