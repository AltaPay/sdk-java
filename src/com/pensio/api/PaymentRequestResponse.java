package com.pensio.api;

import java.net.URL;

public interface PaymentRequestResponse {

	public abstract URL getUrl();
    public abstract String getPaymentRequestId();

}