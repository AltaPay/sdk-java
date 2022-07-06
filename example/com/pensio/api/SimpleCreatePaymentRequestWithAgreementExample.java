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
import com.pensio.api.request.AgreementConfig;

public class SimpleCreatePaymentRequestWithAgreementExample {

	//This is the URL to connect to your gateway instance. If you are in doubt contact support.
	//For test, use: testgateway.altapaysecure.com
	private static String pgwUrl = "https://testgateway.altapaysecure.com/";
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

		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.unscheduled);
		agreementConfig.setAgreementUnscheduledType(AgreementUnscheduledType.incremental);
		pr.setAgreementConfig(agreementConfig);
                
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
