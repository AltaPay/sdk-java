package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.CardWalletSessionRequest;
import com.pensio.api.request.provider.ApplePayRequestData;
import com.pensio.api.request.provider.ApplePaySource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class MerchantApi_ApplePayMappingTests {

    @Test
    void testCardWalletSessionWithApplePaySource() throws PensioAPIException {

        final var validationUrl = "https://apple-pay-gateway.apple.com/paymentservices/startSession";
        final var domain = "checkout.altapaysecure.com";
        ApplePaySource source = ApplePaySource.APPLE_PAY_JS_API;

        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) throws PensioAPIException {
                Assertions.assertEquals("cardWallet/session", method);
                Assertions.assertEquals(HttpMethod.POST, httpMethod);
                Assertions.assertEquals(validationUrl, requestVars.get("applePayRequestData[validationUrl]"));
                Assertions.assertEquals(domain, requestVars.get("applePayRequestData[domain]"));
                Assertions.assertEquals(source.name(), requestVars.get("applePayRequestData[source]"));

                return null;
            }
        };

        ApplePayRequestData applePayRequestData = new ApplePayRequestData();
        applePayRequestData.setValidationUrl(validationUrl);
        applePayRequestData.setDomain(domain);
        applePayRequestData.setSource(source);

        CardWalletSessionRequest request = new CardWalletSessionRequest("ApplePay Test Terminal", "order123", Amount.get(100, Currency.DKK));
        request.setApplePayRequestData(applePayRequestData);

        api.cardWalletSession(request);
    }
}
