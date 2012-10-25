package com.pensio.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;

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
			.setAmount(Amount.get(1.00, Currency.EUR))
			.setShopOrderId("orderid")
			.setTerminal("Pensio Test Terminal")
		);
		
		assertNotNull(result.getUrl());
	}

	@Test
	public void createMotoReservation() throws Throwable 
	{
		APIResponse result = api.reservation((PaymentReservationRequest) new PaymentReservationRequest()
			.setAmount(Amount.get(1.00, Currency.EUR))
			.setShopOrderId("orderid")
			.setTerminal("Pensio Test Terminal")
			.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
		);
		
		assertEquals("Success",result.getBody().getResult());
	}
}
