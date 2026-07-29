package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Body;
import com.pensio.api.request.CreateCheckoutSessionRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MerchantApi_CheckoutSessionParameterTests {

    @Test
    void createCheckoutSessionEmitsRiskManagerAndMaxConversionParams() throws PensioAPIException {
        final var api = new PensioMerchantAPI("http://base", "user", "pass") {
            @Override
            protected APIResponse getAPIResponse(String method, HttpMethod httpMethod, Map<String, String> requestVars) {
                assertEquals("checkoutSession", method);
                assertEquals(HttpMethod.POST, httpMethod);
                assertEquals("group_rm_1", requestVars.get("risk_manager_policy_group"));
                assertEquals("policy_rm_1", requestVars.get("risk_manager_policy"));
                assertEquals("group_mc_1", requestVars.get("max_conversion_policy_group"));
                assertEquals("policy_mc_1", requestVars.get("max_conversion_policy"));
                
                APIResponse response = new APIResponse();
                response.setBody(new Body());
                return response;
            }
        };

        CreateCheckoutSessionRequest request = new CreateCheckoutSessionRequest()
                .setTerminal("Terminal")
                .setAmount(Amount.get(100, Currency.DKK))
                .setShopOrderId("order123")
                .setRiskManagerPolicyGroup("group_rm_1")
                .setRiskManagerPolicy("policy_rm_1")
                .setMaxConversionPolicyGroup("group_mc_1")
                .setMaxConversionPolicy("policy_mc_1");

        api.createCheckoutSession(request);
    }
}
