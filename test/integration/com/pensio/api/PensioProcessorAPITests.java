package com.pensio.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.PensioAPIException;
import com.pensio.api.PensioMerchantAPI;
import com.pensio.api.PensioProcessorAPI;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.PaymentRequest;

public class PensioProcessorAPITests extends PensioAbstractAPITest
{
	private PensioProcessorAPI api; 
	
	@Before
	public void setUp() 
		throws Exception 
	{
		String apiUrl = System.getProperty("pensio.TestUrl","http://gateway4.rolandas.earth.pensio.com/");
		String username = System.getProperty("pensio.TestApiUsername","shop api");
		String password = System.getProperty("pensio.TestApiPassword","testpassword");
		api = new PensioProcessorAPI(apiUrl, username, password);
	}


	@Test
	public void ReservationOfFixedAmount_AllParametersAreThere_ResultIsSuccess() throws Throwable 
	{
		APIResponse result = api.createPaymentRequest(getParams());
		
		assertEquals("Success", result.getBody().getResult());
	}


	private String whiteLabelName()
	{
		return "Altapay";
	}
	private String getTerminalName()
	{
		return whiteLabelName() + " Test Terminal";
	}

	protected HashMap<String, String> getParams()
	{
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("terminal", getTerminalName());
		params.put("payment_source", "eCommerce");
		params.put("amount", "100");
		params.put("currency", "978");
		params.put("shop_orderid", "Test Parameters " + System.currentTimeMillis());
		params.put("cardnum", "4111111111111111");
		params.put("eyear", "2015");
		params.put("emonth", "12");
		params.put("cvc", "111");

		return params;
	}

}
