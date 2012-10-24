package com.pensio.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PensioMerchantAPITest 
{
	private PensioMerchantAPI api;

	@Before
	public void setUp() 
		throws Exception 
	{
		api = new PensioMerchantAPI("http://gateway.dev.pensio.com", "shop api", "testpassword");
	}

	@Test
	public void createPaymentRequest() throws Throwable 
	{
		PaymentRequestResponse result = api.createPaymentRequest(new PaymentRequest()
			.setAmount("1.00")
			.setCurrency("EUR")
			.setShopOrderId("orderid")
			.setTerminal("Pensio Test Terminal")
		);
		
		assertNotNull(result.getUrl());
	}

}
