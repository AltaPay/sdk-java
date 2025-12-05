package com.pensio.response;

public record AuthenticationResponse(boolean authenticated, String version) {
}
