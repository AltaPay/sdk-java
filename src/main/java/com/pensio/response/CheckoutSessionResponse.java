package com.pensio.response;

import com.pensio.api.SessionStatus;

public class CheckoutSessionResponse {
    private String sessionId;
    private SessionStatus sessionStatus;

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
}
