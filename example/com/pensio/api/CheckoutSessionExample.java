package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.request.CreateCheckoutSessionRequest;
import com.pensio.response.CheckoutSessionResponse;

import java.util.Arrays;
import java.util.Random;

public class CheckoutSessionExample {
    private final static String apiUrl = System.getProperty("pensio.TestUrl", "https://testgateway.altapaysecure.com/");
    private final static String username = System.getProperty("pensio.TestApiUsername", "shop api");
    private final static String password = System.getProperty("pensio.TestApiPassword", "testpassword");
    private final static PensioMerchantAPI api = new PensioMerchantAPI(apiUrl, username, password);

    public static void main(String args[]) throws PensioAPIException {
        String orderId = "CheckoutSessionExample_" + new Random().nextInt();

        // 1. Create a Checkout Session
        CreateCheckoutSessionRequest createRequest = new CreateCheckoutSessionRequest();
        createRequest.setTerminal("AltaPay Test Terminal");
        createRequest.setShopOrderId(orderId);
        createRequest.setAmount(Amount.get(100.00, Currency.EUR));
        createRequest.setTerminals(Arrays.asList("AltaPay Test Terminal"));

        CheckoutSessionResponse createResponse = api.createCheckoutSession(createRequest);
        System.out.println("Checkout Session Created:");
        System.out.println("Session ID: " + createResponse.getSessionId());
        System.out.println("Session Status: " + createResponse.getSessionStatus());
    }
}
