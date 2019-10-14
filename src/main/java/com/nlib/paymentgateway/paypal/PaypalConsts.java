package com.nlib.paymentgateway.paypal;

/**
 * PaypalConsts
 */
public final class PaypalConsts {
    public static String INTENT_CAPTURE = "CAPTURE";
    public static String INTENT_AUTHORIZE = "AUTHORIZE";

    public static String LANDING_BILLING = "BILLING";
    public static String LANDING_LOGIN = "LOGIN";
    public static String LANDING_NONE = "NO_PREFERENCE";

    public static String SHIPPING_GET_FROM_FILE = "GET_FROM_FILE";
    public static String SHIPPING_NONE = "NO_SHIPPING";
    public static String SHIPPING_SET_PROVIDED = "SET_PROVIDED_ADDRESS";

    /*
     * Class cannot be instantiated 
     */
    private PaypalConsts(){}
    
}