package com.nlib.paymentgateway;

/**
 * PaymentType
 */
public enum PaymentType {
    Paypal(1),
    ;

    private int typeID;
    PaymentType(int typeID){
        this.typeID = typeID;
    }

    public int getTypeID(){
        return typeID;
    }
}