package com.pensio.api;

import java.net.URL;

public class PaymentRequestResponseImpl implements PaymentRequestResponse 
{
	private URL url;

	/* (non-Javadoc)
	 * @see com.pensio.api.PaymentRequestResponse#getUrl()
	 */
	@Override
	public URL getUrl() 
	{
		return url;
	}

	public PaymentRequestResponse setUrl(URL url) 
	{
		this.url = url;
		return this;
	}
}
