package com.ratepay.bugtracker.bug.exception;

public class NoRecordFoundException extends RuntimeException{
    public NoRecordFoundException(String message) {
        super(message);
    }
}
