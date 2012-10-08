package com.pensio.api;

public class PaymentRequestResponseImpl implements PaymentRequestResponse 
{
	private String url;

	/* (non-Javadoc)
	 * @see com.pensio.api.PaymentRequestResponse#getUrl()
	 */
	@Override
	public String getUrl() 
	{
		return url;
	}

	public PaymentRequestResponse setUrl(String url) 
	{
		this.url = url;
		return this;
	}
}
