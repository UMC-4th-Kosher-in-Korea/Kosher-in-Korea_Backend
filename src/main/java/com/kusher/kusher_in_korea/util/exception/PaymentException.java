package com.kusher.kusher_in_korea.util.exception;

public class PaymentException extends Exception {

    private static final long serialVersionUID = 1L;

    public PaymentException(String message) {
        super(message);
    } // constructor #1

    public PaymentException(Exception e) {
        super(e);
    } // constructor #2
}
