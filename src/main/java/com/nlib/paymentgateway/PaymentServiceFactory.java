package com.nlib.paymentgateway;

import com.nlib.paymentgateway.exception.GenericPaymentServiceException;
import com.nlib.paymentgateway.exception.PaymentTypeException;
import com.nlib.paymentgateway.paypal.PaypalService;

/**
 * PaymentServiceFactory
 */
public class PaymentServiceFactory {
    public static PaymentService createService(PaymentType type, String...args) throws PaymentTypeException, GenericPaymentServiceException {
        switch (type.getTypeID()){
            case 1: 
                if(args == null || args.length < 2) throw new GenericPaymentServiceException("Missing parameters for PaypalService");
                return PaypalService.init(args[0], args[1]);
            default: throw new PaymentTypeException("Payment Type not available");
        }
    }
}