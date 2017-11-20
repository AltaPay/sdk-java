package com.pensio.api;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

import com.pensio.Amount;
import com.pensio.AmountConversionException;
import com.pensio.Currency;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentRequestConfig;

public class SimpleCreatePaymentRequestExample {
	
	private static String pgwUrl = "https://vmedev.pensio.com/";
	private static String user = "shop api";
	private static String password = "testpassword";
	
	public static void main(String[] args) throws Throwable {
		
		createPayment();
		
	}
	
	public static String createPayment() throws AmountConversionException, PensioAPIException{
		PensioMerchantAPI api = new PensioMerchantAPI(pgwUrl, user, password);
		PaymentRequest pr = new PaymentRequest();
		pr.setTerminal("AltaPay Test Terminal");
		pr.setAmount(Amount.get("100.00",Currency.DKK));			
		pr.setShopOrderId(getRandonString());
//		PaymentRequestConfig config = new PaymentRequestConfig();
//		config.setCallbackOk("http://google.com");
//		pr.setConfig(config);
		PaymentRequestResponse response = api.createPaymentRequest(pr);
		System.out.println("url: " + response.getUrl() + "\npaymentRequestId: " + response.getPaymentRequestId());
		return response.getPaymentRequestId();
	}
	
	private static String getRandonString(){
		SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[16];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    return encoder.encodeToString(bytes);
	}
}
