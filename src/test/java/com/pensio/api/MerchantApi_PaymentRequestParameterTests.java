package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Body;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MerchantApi_PaymentRequestParameterTests {

    @Test
    void createPaymentRequestEmitsSaleInvoiceNumberParam() throws PensioAPIException {
        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                assertEquals("createPaymentRequest", method);
                assertEquals(HttpMethod.POST, httpMethod);
                assertEquals("INV-12345", requestVars.get("sale_invoice_number"));
                assertNull(requestVars.get("invoiceNumber"));

                APIResponse response = new APIResponse();
                Body body = new Body();
                body.setUrl("http://checkout.pensio.com");
                response.setBody(body);
                return response;
            }
        };

        PaymentRequest<?> request = new PaymentRequest<>()
                .setTerminal("Terminal")
                .setAmount(Amount.get(100, Currency.DKK))
                .setShopOrderId("order123")
                .setSaleInvoiceNumber("INV-12345");

        api.createPaymentRequest(request);
    }

    @Test
    void reservationEmitsSaleInvoiceNumberParam() throws PensioAPIException {
        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                assertEquals("reservation", method);
                assertEquals(HttpMethod.POST, httpMethod);
                assertEquals("INV-99999", requestVars.get("sale_invoice_number"));
                assertNull(requestVars.get("invoiceNumber"));

                APIResponse response = new APIResponse();
                Body body = new Body();
                response.setBody(body);
                return response;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest("order123", "Terminal", Amount.get(100, Currency.DKK));
        request.setSaleInvoiceNumber("INV-99999");

        api.reservation(request);
    }
}
