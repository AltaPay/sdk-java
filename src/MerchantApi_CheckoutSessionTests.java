package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.CreateCheckoutSessionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

class MerchantApi_CheckoutSessionTests {

    @Test
    void testCreateCheckoutSessionMapping() throws PensioAPIException {
        final var terminal = "AltaPay Test Terminal";
        final var shopOrderId = "order123";
        final var amount = Amount.get(100, Currency.DKK);
        final var terminals = Arrays.asList("term1", "term2");

        final var api = new PensioMerchantAPI("https://testgateway.altapaysecure.com/", "shop api", "testpassword") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) throws PensioAPIException {
                Assertions.assertEquals("checkoutSession", method);
                Assertions.assertEquals(HttpMethod.POST, httpMethod);
                Assertions.assertEquals(terminal, requestVars.get("terminal"));
                Assertions.assertEquals(shopOrderId, requestVars.get("shop_orderid"));
                Assertions.assertEquals(amount.getAmountString(), requestVars.get("amount"));
                Assertions.assertEquals(amount.getCurrency().name(), requestVars.get("currency"));
                Assertions.assertEquals("term1", requestVars.get("terminals[0]"));
                Assertions.assertEquals("term2", requestVars.get("terminals[1]"));

                // Return a mock response with session
                APIResponse response = new APIResponse();
                com.pensio.api.generated.Body body = new com.pensio.api.generated.Body();
                com.pensio.api.generated.Session session = new com.pensio.api.generated.Session();
                session.setId("session-123");
                session.setStatus("CREATED");
                body.setSession(session);
                response.setBody(body);
                return response;
            }
        };

        CreateCheckoutSessionRequest request = new CreateCheckoutSessionRequest();
        request.setTerminal(terminal);
        request.setShopOrderId(shopOrderId);
        request.setAmount(amount);
        request.setTerminals(terminals);

        var response = api.createCheckoutSession(request);
        Assertions.assertEquals("session-123", response.getSessionId());
        Assertions.assertEquals(SessionStatus.CREATED, response.getSessionStatus());
    }
}
