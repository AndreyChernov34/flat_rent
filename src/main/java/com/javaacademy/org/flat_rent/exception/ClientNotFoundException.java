package com.javaacademy.org.flat_rent.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String s) {
        super(s);
    }
}
