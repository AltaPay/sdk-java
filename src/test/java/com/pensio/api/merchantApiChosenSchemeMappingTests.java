package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.PaymentReservationRequest;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

class MerchantApiChosenSchemeMappingTests {

    @Test
    void chosenScheme_isForwardedAsTopLevelFormParam_onReservationCall() throws PensioAPIException {
        AtomicReference<Map<String, String>> capturedParams = new AtomicReference<>();
        AtomicReference<String> capturedMethod = new AtomicReference<>();

        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                capturedMethod.set(method);
                capturedParams.set(requestVars);
                return null;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest(
                "order-1", "terminal-1", Amount.get(10.0, Currency.EUR));
        request.setChosenScheme("Visa");

        api.reservation(request);

        Assertions.assertEquals("reservation", capturedMethod.get());
        Assertions.assertEquals("Visa", capturedParams.get().get("chosen_scheme"));
    }

    @Test
    void chosenScheme_null_isNotEmittedOnTheWire() throws PensioAPIException {
        AtomicReference<Map<String, String>> capturedParams = new AtomicReference<>();

        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                capturedParams.set(requestVars);
                return null;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest(
                "order-1", "terminal-1", Amount.get(10.0, Currency.EUR));

        api.reservation(request);

        Assertions.assertFalse(
                capturedParams.get().containsKey("chosen_scheme"),
                "chosen_scheme should not be present on the wire when not set");
    }

    @Test
    void chosenScheme_mastercard_isForwarded() throws PensioAPIException {
        AtomicReference<Map<String, String>> capturedParams = new AtomicReference<>();

        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                capturedParams.set(requestVars);
                return null;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest(
                "order-2", "terminal-2", Amount.get(25.0, Currency.USD));
        request.setChosenScheme("Mastercard");

        api.reservation(request);

        Assertions.assertEquals("Mastercard", capturedParams.get().get("chosen_scheme"));
    }
}
