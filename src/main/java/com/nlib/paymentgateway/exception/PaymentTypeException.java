package com.nlib.paymentgateway.exception;

/**
 * PaymentTypeException
 */
public class PaymentTypeException extends Exception{
    
    private static final long serialVersionUID = 1L;

    public PaymentTypeException(Exception e) {
        super(e);
    }
    public PaymentTypeException(String errMsg){
        super(errMsg);
    }
    public PaymentTypeException(String errMsg, Exception e){
        super(errMsg, e);
    }
}