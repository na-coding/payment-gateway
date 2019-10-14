package com.nlib.paymentgateway;

/**
 * PaymentService
 */
public abstract interface PaymentService {
    public static PaymentService init(String clientID, String clientSecret){return null;}
}