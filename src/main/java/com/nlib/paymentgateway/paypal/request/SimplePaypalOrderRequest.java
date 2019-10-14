package com.nlib.paymentgateway.paypal.request;

import java.util.ArrayList;
import java.util.List;

import com.paypal.orders.Item;
import com.paypal.orders.Money;

/**
 * SimplePaypalOrderRequest
 */
public class SimplePaypalOrderRequest {
    public SimplePaypalOrderRequest(){}

    private String brandName;
    public String brandName(){return brandName;}
    public SimplePaypalOrderRequest withBrandName(String brandName){
        this.brandName = brandName;
        return this;
    }

    private String description;
    public String description(){return description;}
    public SimplePaypalOrderRequest withDescription(String description){
        this.description = description;
        return this;
    }

    private String customId;
    public String customId(){return customId;}
    public SimplePaypalOrderRequest withCustomId(String customId){
        this.customId = customId;
        return this;
    }

    private List<Item> items = new ArrayList<Item>();
    public List<Item> items(){return items;}
    public SimplePaypalOrderRequest withItem(String name, String value, String currency, String amount){
        items.add(new Item().name(name).unitAmount(new Money().currencyCode(currency).value(value)).quantity(amount));
        return this;
    }

    private String currency;
    public String currency(){return currency;}
    public SimplePaypalOrderRequest withCurrency(String currency){
        this.currency = currency;
        return this;
    }

    private String totalAmount;
    public String totalAmount(){return totalAmount;}
    public SimplePaypalOrderRequest withTotalAmount(String totalAmount){
        this.totalAmount = totalAmount;
        return this;
    }

    // class Item {
    //     private String name;
    //     private String value;
    //     private String amount;

    //     public Item(String name, String value, String amount){
    //         this.name = name;
    //         this.value = value;
    //         this.amount = amount;
    //     }

    //     public String name(){return name;}
    //     public String value(){return value;}
    //     public String amount(){return amount;}
    // }

    
    
}