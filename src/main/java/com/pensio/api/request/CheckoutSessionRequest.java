package com.pensio.api.request;

import com.pensio.api.SessionStatus;

public class CheckoutSessionRequest {
    private String sessionId;
    private SessionStatus status;

    public CheckoutSessionRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    public CheckoutSessionRequest(String sessionId, SessionStatus status) {
        this.sessionId = sessionId;
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public SessionStatus getStatus() {
        return status;
    }

}
