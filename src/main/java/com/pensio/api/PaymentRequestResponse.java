package com.pensio.api;

import java.net.URL;

public interface PaymentRequestResponse {

	public abstract URL getUrl();
    public abstract URL getAppUrl();
    public abstract String getPaymentRequestId();
}