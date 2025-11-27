package com.pensio.api.request;

import com.pensio.api.SessionStatus;

public record UpdateCheckoutSessionRequest(String sessionId, SessionStatus status) {
}
