package com.nlib.paymentgateway.exception;

/**
 * GenericPaymentServiceException
 */
public class GenericPaymentServiceException extends Exception{

    public GenericPaymentServiceException(Exception e) {
        super(e);
    }
    public GenericPaymentServiceException(String errMsg){
        super(errMsg);
    }
    public GenericPaymentServiceException(String errMsg, Exception e){
        super(errMsg, e);
    }

}