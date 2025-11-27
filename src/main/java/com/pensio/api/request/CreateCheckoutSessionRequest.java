package com.pensio.api.request;

public class CreateCheckoutSessionRequest {
    public String getSessionId() {
        return sessionId;
    }

    private String sessionId;

    public CreateCheckoutSessionRequest() {
    }

    public CreateCheckoutSessionRequest(String sessionId) {
        this.sessionId = sessionId;
    }

}
