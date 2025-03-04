package com.javaacademy.org.flat_rent.exception;

public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException(String s) {
        super(s);
    }
}
