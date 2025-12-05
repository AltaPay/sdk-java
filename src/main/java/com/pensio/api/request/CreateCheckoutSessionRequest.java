package com.pensio.api.request;

import java.util.List;

public class CreateCheckoutSessionRequest extends PaymentRequest<CreateCheckoutSessionRequest> {

    private List<String> terminals;

    public List<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }
}
