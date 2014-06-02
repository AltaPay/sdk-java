package com.pensio.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.pensio.api.request.CreditCard;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;

public class PensioProcessorAPITests extends PensioAbstractAPITest
{
	private PensioProcessorAPI api; 
	
	@Before
	public void setUp() 
		throws Exception 
	{
		String apiUrl = System.getProperty("pensio.TestUrl","http://gateway.dev.pensio.com/");
		String username = System.getProperty("pensio.TestApiUsername","shop api");
		String password = System.getProperty("pensio.TestApiPassword","testpassword");
		api = new PensioProcessorAPI(apiUrl, username, password);
	}


	@Test
	public void ReservationOfFixedAmount_AllParametersAreThere_ResultIsSuccess() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2015").setCvc("111"));
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("Success", result.getBody().getResult());
	}

//	@Test
//	public void ReservationOfFixedAmount_ExpiryYearIsLessThan4Digits_ResultIsError() throws Throwable 
//	{
//		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
//		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "999").setCvc("111"));
//		APIResponse result = api.initiatePaymentRequest(request);
//		
//		assertEquals("Invalid expiry year", result.getHeader().getErrorMessage());
//	}

//	@Test
//	public void ReservationOfFixedAmount_ExpiryYearIsMoreThan4Digits_ResultIsError() throws Throwable 
//	{
//		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
//		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "20125").setCvc("111"));
//		APIResponse result = api.initiatePaymentRequest(request);
//		
//		assertEquals("Invalid expiry year", result.getHeader().getErrorMessage());
//	}

//	@Test
//	public void ReservationOfFixedAmount_ExpiryYearContainsAnythingButNumbers_ResultIsError() throws Throwable 
//	{
//		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
//		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "201A").setCvc("111"));
//		APIResponse result = api.initiatePaymentRequest(request);
//		
//		assertEquals("Invalid expiry year", result.getHeader().getErrorMessage());
//	}

//	@Test
//	public void ReservationOfFixedAmount_CurrencyIsInvalid_ResultIsFailed() throws Throwable 
//	{
//		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
//		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "201A").setCvc("111"));
//		APIResponse result = api.initiatePaymentRequest(request);
//		
////		assertEquals(InvalidCurrencyException, result.getHeader().getErrorCode());
//		assertTrue(result.getHeader().getErrorMessage().contains("No such currency: not numeric"));
//	}


	private String whiteLabelName()
	{
		return "Altapay";
	}
	private String getTerminalName()
	{
		return whiteLabelName() + " Test Terminal";
	}

}
