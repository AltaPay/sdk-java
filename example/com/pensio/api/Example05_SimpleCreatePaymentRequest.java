package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.request.AuthType;
import com.pensio.api.request.OrderLine;
import com.pensio.api.request.PaymentRequest;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

public class Example05_SimpleCreatePaymentRequest
{
	private final static String apiUrl = System.getProperty("pensio.TestUrl", "http://gateway.dev.earth.pensio.com/");
	private final static String username = System.getProperty("pensio.TestApiUsername", "shop api");
	private final static String password = System.getProperty("pensio.TestApiPassword", "testpassword");
	private final static PensioMerchantAPI api = new PensioMerchantAPI(apiUrl, username, password);

	public static void main(String args[]) throws PensioAPIException
	{

		String orderId = "Example05_SimpelRequest_" + new Random().nextInt();

		PaymentRequest request = new PaymentRequest(orderId, "AltaPay Test Terminal", Amount.get(5.5, Currency.DKK));

		request.setAuthType(AuthType.payment);

		OrderLine ol1 = new OrderLine("description 1", "id 01", 1, 1.1);
		OrderLine ol2 = new OrderLine("description 2", "id 02", 2, 2.2);
		ol1.setGoodsType("Item");
		ol1.setTaxAmount(0.15);
		ol1.setTaxPercent(10);
		ol2.setGoodsType("Item");
		request.setOrderLines(Arrays.asList(ol1, ol2));

		PaymentRequestResponse result = api.createPaymentRequest(request);

		assertNotNull(result.getUrl());

		// Access the url below and use the social security number 0801363945 in the page form to complete the Klarna order
		System.out.println(result.getUrl());
	}
}
