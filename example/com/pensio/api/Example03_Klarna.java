package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.request.*;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

/**
 * Created by emerson on 6/28/17.
 */
public class Example03_Klarna
{

	private final static String apiUrl = System.getProperty("pensio.TestUrl", "http://gateway.dev.earth.pensio.com/");
	private final static String username = System.getProperty("pensio.TestApiUsername", "shop api");
	private final static String password = System.getProperty("pensio.TestApiPassword", "testpassword");
	private final static PensioMerchantAPI api = new PensioMerchantAPI(apiUrl, username, password);

	public static void main(String args[]) throws PensioAPIException
	{

		String orderId = "Example03_Klarna_" + new Random().nextInt();

		PaymentRequest request = new PaymentRequest(orderId, "AltaPay Klarna DK", Amount.get(5.5, Currency.DKK));

		request.setAuthType(AuthType.payment);

		OrderLine ol1 = new OrderLine("description 1", "id 01", 1, 1.1);
		OrderLine ol2 = new OrderLine("description 2", "id 02", 2, 2.2);
		ol1.setGoodsType("Item");
		ol2.setGoodsType("Item");
		request.setOrderLines(Arrays.asList(ol1, ol2));

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setEmail("myuser@mymail.com");
		customerInfo.setUsername("myuser");
		customerInfo.setCustomerPhone("20123456");
		customerInfo.setBankName("My Bank");
		customerInfo.setBankPhone("+45 12-34 5678");

		CustomerInfoAddress billingAddress = new CustomerInfoAddress();
		billingAddress.setAddress("Sæffleberggate 56,1 mf");
		billingAddress.setCity("Varde");
		billingAddress.setCountry("DK");
		billingAddress.setFirstname("Testperson-dk");
		billingAddress.setLastname("Approved");
		billingAddress.setRegion("DK");
		billingAddress.setPostal("6800");
		customerInfo.setBillingAddress(billingAddress);
		customerInfo.setShippingAddress(billingAddress);

		request.setCustomerInfo(customerInfo);

		PaymentRequestResponse result = api.createPaymentRequest(request);

		assertNotNull(result.getUrl());

		// Access the url below and use the social security number 0801363945 in the page form to complete the Klarna order
		System.out.println(result.getUrl());


	}

}
