package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.PassCard;
import com.pensio.api.request.PaymentReservationRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class MerchantApi_AcquirerTransactionDataEmissionTests {

    @Test
    void reservationEmitsAcquirerTransactionDataParams() throws PensioAPIException {
        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                assertEquals("reservation", method);
                assertEquals(HttpMethod.POST, httpMethod);
                assertEquals("32", requestVars.get("acquirerTransactionData[passcard][creditcode]"));
                assertEquals("001", requestVars.get("acquirerTransactionData[passcard][paymentoccurrence]"));
                return null;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest("order123", "Terminal", Amount.get(100, Currency.DKK));
        request.getAcquirerTransactionData()
            .add(PassCard.GROUP, PassCard.CREDITCODE, "32")
            .add(PassCard.GROUP, PassCard.PAYMENTOCCURRENCE, "001");

        api.reservation(request);
    }

    @Test
    void reservationOmitsAcquirerTransactionDataParamsWhenEmpty() throws PensioAPIException {
        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                assertEquals("reservation", method);
                assertEquals(HttpMethod.POST, httpMethod);
                assertFalse(
                    requestVars.keySet().stream().anyMatch(k -> k.startsWith("acquirerTransactionData[")),
                    "no key starting with acquirerTransactionData[ should be emitted when empty"
                );
                return null;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest("order123", "Terminal", Amount.get(100, Currency.DKK));

        api.reservation(request);
    }

    @Test
    void aRepeatedKeyEmitsIndexedParamsSoNeitherValueIsLost() throws PensioAPIException {
        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                assertEquals("CRF_A", requestVars.get("acquirerTransactionData[metadata][contractNumber][0]"));
                assertEquals("CRF_B", requestVars.get("acquirerTransactionData[metadata][contractNumber][1]"));
                assertNull(requestVars.get("acquirerTransactionData[metadata][contractNumber]"),
                    "the unindexed name must not also be emitted, or PHP sees a scalar and a list");
                return null;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest("order123", "Terminal", Amount.get(100, Currency.DKK));
        request.getAcquirerTransactionData()
            .add("metadata", "contractNumber", "CRF_A")
            .add("metadata", "contractNumber", "CRF_B");

        api.reservation(request);
    }

    @Test
    void aSingleValueEmitsTheUnindexedName() throws PensioAPIException {
        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                assertEquals("CRF_A", requestVars.get("acquirerTransactionData[metadata][contractNumber]"));
                assertNull(requestVars.get("acquirerTransactionData[metadata][contractNumber][0]"),
                    "DELIBERATE: sdk-java ships before gateway4 learns to accept lists, and an old "
                    + "gateway4 drops the indexed form on its is_scalar gate. Emitting a single "
                    + "value unindexed is what keeps live PassCard payments intact across that "
                    + "release. Do not 'simplify' this to always-indexed.");
                return null;
            }
        };

        PaymentReservationRequest request = new PaymentReservationRequest("order123", "Terminal", Amount.get(100, Currency.DKK));
        request.getAcquirerTransactionData().add("metadata", "contractNumber", "CRF_A");

        api.reservation(request);
    }
}
