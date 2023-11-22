package com.pensio.api;

import java.net.URL;

public class PaymentRequestResponseImpl implements PaymentRequestResponse 
{
	private URL url;
	private URL appUrl;
    private String paymentRequestId;

	/* (non-Javadoc)
	 * @see com.pensio.api.PaymentRequestResponse#getUrl()
	 */
	@Override
	public URL getUrl() 
	{
		return url;
	}

	public PaymentRequestResponseImpl setUrl(URL url)
	{
		this.url = url;
		return this;
	}

	@Override
	public URL getAppUrl()
	{
		return appUrl;
	}

	public PaymentRequestResponseImpl setAppUrl(URL appUrl)
	{
		this.appUrl = appUrl;
		return this;
	}

    @Override
    public String getPaymentRequestId()
    {
        return paymentRequestId;
    }

    public PaymentRequestResponseImpl setPaymentRequestId(String paymentRequestId)
    {
        this.paymentRequestId = paymentRequestId;
        return this;
    }
}
