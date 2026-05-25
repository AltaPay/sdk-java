package com.pensio.response;

import com.pensio.api.SessionStatus;

import java.util.List;

public class CheckoutSessionResponse {
    private String sessionId;
    private SessionStatus sessionStatus;
    private List<String> supportedTerminals;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public List<String> getSupportedTerminals() {
        return supportedTerminals;
    }

    public void setSupportedTerminals(List<String> supportedTerminals) {
        this.supportedTerminals = supportedTerminals;
    }
}
