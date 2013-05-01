package com.pensio.api;

import java.net.URL;

public class MultiPaymentRequestResponseImpl
	implements MultiPaymentRequestResponse 
{
	private URL url;

	/* (non-Javadoc)
	 * @see com.pensio.api.MultiPaymentRequestResponse#getUrl()
	 */
	@Override
	public URL getUrl() 
	{
		return url;
	}

	public MultiPaymentRequestResponse setUrl(URL url) 
	{
		this.url = url;
		return this;
	}
}
