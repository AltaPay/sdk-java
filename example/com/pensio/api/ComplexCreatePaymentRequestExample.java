package com.pensio.api;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.request.CustomerInfo;
import com.pensio.api.request.CustomerInfoAddress;
import com.pensio.api.request.Gender;
import com.pensio.api.request.OrderLine;
import com.pensio.api.request.PaymentRequest;

public class ComplexCreatePaymentRequestExample {
	
	private static String pgwUrl = "https://vmedev.pensio.com/";
	private static String user = "shop api";
	private static String password = "testpassword";
	
	public static void main(String[] args) throws Throwable {
		PensioMerchantAPI api = new PensioMerchantAPI(pgwUrl, user, password);
		PaymentRequest pr = new PaymentRequest();
		pr.setTerminal("AltaPay Test Terminal");
		pr.setAmount(Amount.get("100.00",Currency.DKK));			
		pr.setShopOrderId(getRandonString());		
		pr.setOrderLines(createOrderLines());
		pr.setCustomerInfo(createCustomerInfo());
		PaymentRequestResponse response = api.createPaymentRequest(pr);		
		System.out.println("url: " + response.getUrl() + "\npaymentRequestId: " + response.getPaymentRequestId());

		
	}
	
	
	private static CustomerInfo createCustomerInfo(){
		CustomerInfo customer = new CustomerInfo();
		customer.setEmail("a@a.a");
		customer.setGender(Gender.Male);
		customer.setUsername("user1");
		customer.setBillingAddress(new CustomerInfoAddress().setFirstname("John").setLastname("Wick").setCity("NYC"));
		customer.setShippingAddress(new CustomerInfoAddress().setFirstname("Clark").setLastname("Kent").setCity("LA"));
		return customer;
	}
	
	private static List<OrderLine> createOrderLines(){
		List<OrderLine> orderLines = new ArrayList<OrderLine>();
		for(int i = 0; i<2; i++){
			OrderLine orderLine = new OrderLine("Orderline"+(i+1), "item"+(i+1), i+1, 100);
			orderLines.add(orderLine);
		}
		return orderLines;
	}
	
	private static String getRandonString(){
		SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[16];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    return encoder.encodeToString(bytes);
	}
}
