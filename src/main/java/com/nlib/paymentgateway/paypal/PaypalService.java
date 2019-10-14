package com.nlib.paymentgateway.paypal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.braintreepayments.http.HttpResponse;
import com.nlib.paymentgateway.PaymentService;
import com.nlib.paymentgateway.paypal.request.SimplePaypalOrderRequest;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Item;
import com.paypal.orders.Money;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;

/**
 * PaypalService
 */
public class PaypalService implements PaymentService {
    private PayPalEnvironment env;
    private PayPalHttpClient client;

    public static PaymentService init(String clientID, String clientSecret) {
        return new PaypalService(clientID, clientSecret);
    }

    private PaypalService(String clientID, String clientSecret) {
        env = new PayPalEnvironment.Sandbox(clientID, clientSecret);
        // env = new PayPalEnvironment.Live(clientID, clientSecret);

        client = new PayPalHttpClient(env);
    }

    // public HttpResponse<Order> createOrder() throws IOException {
    //     OrdersCreateRequest request = new OrdersCreateRequest();
    //     request.prefer("return=representation");
    //     request.requestBody(buildRequestBody());

    //     HttpResponse<Order> response = client.execute(request);
    //     return response;
    // }

    public HttpResponse<Order> createOrder(OrdersCreateRequest request) throws IOException{
        return client.execute(request);
    }

    public HttpResponse<Order> createOrder(SimplePaypalOrderRequest request) throws IOException{
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest();
        
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.intent(PaypalConsts.INTENT_CAPTURE);

        ApplicationContext applicationContext = new ApplicationContext()
            .brandName(request.brandName())
            .landingPage(PaypalConsts.LANDING_BILLING)
            .shippingPreference(PaypalConsts.SHIPPING_GET_FROM_FILE);
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
            .description(request.description())
            .customId(request.customId())
            .amount(
                new AmountWithBreakdown()
                    .currencyCode(request.currency())
                    .value(request.totalAmount())
                    .breakdown(
                        new AmountBreakdown()
                            .itemTotal(new Money().currencyCode(request.currency()).value(request.totalAmount()))
                    )
            )
            .items(request.items());

        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);

        ordersCreateRequest.requestBody(orderRequest);

        return client.execute(ordersCreateRequest);
    }

    public HttpResponse<Order> captureOrder(String orderID) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderID);
        request.requestBody(new OrderRequest());

        HttpResponse<Order> response = client.execute(request);
        return response;
    }

    // private OrderRequest buildRequestBody(){
    //     OrderRequest orderRequest = new OrderRequest();
    //     orderRequest.intent("CAPTURE");

    //     ApplicationContext applicationContext = new ApplicationContext().brandName("EXAMPLE_INC").landingPage("BILLING").shippingPreference("GET_FROM_FILE");
    //     orderRequest.applicationContext(applicationContext);

    //     List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
    //     PurchaseUnitRequest purchaseUnitRequest =  new PurchaseUnitRequest()
    //         .description("Sample purchase")
    //         .customId("CUST_Sample")
    //         .softDescriptor("Sample")
    //         .amount(new AmountWithBreakdown().currencyCode("PHP").value("50.00").breakdown(new AmountBreakdown().
    //             itemTotal(new Money().currencyCode("PHP").value("40.00")).
    //             handling(new Money().currencyCode("PHP").value("10.00"))))
    //         .items(new ArrayList<Item>(){
    //             {
    //                 add(new Item().name("TestItem").description("Testing item").unitAmount(new Money().currencyCode("PHP").value("40.00")).quantity("1"));
    //             }
    //         });
    //         // .shipping(new ShippingDetails().name(new Name().fullName("Nigel Aoyang")).addressPortable(new AddressPortable().addressLine1("QuezonCity").adminArea2("QC").postalCode("1105").countryCode("PH")));

    //     purchaseUnitRequests.add(purchaseUnitRequest);
    //     orderRequest.purchaseUnits(purchaseUnitRequests);
        
    //     return orderRequest;
    // }

}