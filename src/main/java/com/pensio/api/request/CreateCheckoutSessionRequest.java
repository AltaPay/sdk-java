package com.pensio.api.request;

import java.util.List;

public class CreateCheckoutSessionRequest extends PaymentRequest {

    private List<String> terminals;

    public CreateCheckoutSessionRequest() {
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }
}
