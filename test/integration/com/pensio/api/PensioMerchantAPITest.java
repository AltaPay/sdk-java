package com.pensio.api;

import static org.junit.Assert.*;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import request.CaptureReservationRequest;
import request.CreditCard;
import request.PaymentRequest;
import request.PaymentReservationRequest;

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
			.setShopOrderId(getOrderId())
			.setTerminal("Pensio Test Terminal")
		);
		
		assertNotNull(result.getUrl());
	}

	@Test
	public void createMotoReservation() throws Throwable 
	{
		APIResponse result = api.reservation(new PaymentReservationRequest()
			.setAmount(Amount.get(1.00, Currency.EUR))
			.setShopOrderId(getOrderId())
			.setTerminal("Pensio Test Terminal")
			.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
		);
		
		assertEquals("Success",result.getBody().getResult());
	}
	
	@Test
	public void captureReservation() throws Throwable 
	{
		String orderId = getOrderId();
		APIResponse result = api.reservation(new PaymentReservationRequest()
			.setAmount(Amount.get(3.00, Currency.EUR))
			.setShopOrderId(orderId)
			.setTerminal("Pensio Test Terminal")
			.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.capture(
			new CaptureReservationRequest(paymentId)
			.setAmount(Amount.get(2.00, Currency.EUR))
			.setInvoiceNumber(orderId)
			.setReconciliationIdentifier(orderId)
			.setSalesTax("0.5")
		);
		
		assertEquals("Success",captureResult.getBody().getResult());
	}

	private String getOrderId() throws Throwable
	{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		return "Test_"+Hex.encodeHexString(digest.digest(String.valueOf(System.currentTimeMillis()).getBytes()));
	}
}
