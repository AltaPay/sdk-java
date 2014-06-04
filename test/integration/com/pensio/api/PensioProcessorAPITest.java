package com.pensio.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.PensioAPIException;
import com.pensio.api.PensioProcessorAPI;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.AuthType;
import com.pensio.api.request.CreditCard;
import com.pensio.api.request.CustomerInfo;
import com.pensio.api.request.CustomerInfoAddress;
import com.pensio.api.request.PaymentReservationRequest;
import com.pensio.api.request.PaymentReservationWithAddressRequest;
import com.pensio.api.request.Verify3dRequest;

public class PensioProcessorAPITest extends PensioAbstractAPITest
{
	protected PensioProcessorAPI	api;
	
	@Before
	public void setUp()
		throws Exception, Throwable
	{
		String apiUrl = System.getProperty("pensio.TestUrl","http://gateway.dev.pensio.com/");
		String username = System.getProperty("pensio.TestApiUsername","shop api");
		String password = System.getProperty("pensio.TestApiPassword","testpassword");
		api = new PensioProcessorAPI(apiUrl, username, password);
	}
	
	/*
	 * ReservationOfFixedAmountActionTests
	 */
	@Test
	public void ReservationOfFixedAmount_AllParametersAreThere_ResultIsSuccess() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void ReservationOfFixedAmount_SomeParametersIsMissing_ResultIsFailed() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		String message = "";

		try
		{
			APIResponse result = api.initiatePaymentRequest(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("Missing parameter: payment_source"));
	}
	
	@Test
	public void ReservationOfFixedAmount_ExpiryYearIsLessThan4Digits_ResultIsError() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "999").setCvc("111"));
		String message = "";

		try
		{
			APIResponse result = api.initiatePaymentRequest(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("Invalid expiry year[84237512]"));
	}

	@Test
	public void ReservationOfFixedAmount_ExpiryYearIsMoreThan4Digits_ResultIsError() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "20125").setCvc("111"));
		String message = "";

		try
		{
			APIResponse result = api.initiatePaymentRequest(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("Invalid expiry year[84237512]"));
	}

	@Test
	public void ReservationOfFixedAmount_ExpiryYearContainsAnythingButNumbers_ResultIsError() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "201A").setCvc("111"));
		String message = "";

		try
		{
			APIResponse result = api.initiatePaymentRequest(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("Invalid expiry year[84237512]"));
	}


	@Test
	public void ReservationOfFixedAmount_CreatesATransaction_StatusIsPreauth() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("preauth", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}

	
	@Test
	public void ReservationOfFixedAmount_PaymentSourceIsSetForMoto_ResultIsSuccess() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("moto").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("Success", result.getBody().getResult());
	}
	
	@Test
	public void ReservationOfFixedAmount_PaymentSourceIsSetForMobi_ResultIsSuccess() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("mobi").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("Success", result.getBody().getResult());
	}
	
	@Test
	public void ReservationOfFixedAmount_InvalidPaymentSource_Fails() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("totaltSygPaymentSource").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111")).setAuthType(AuthType.subscription);
		String message = "";

		try
		{
			APIResponse result = api.initiatePaymentRequest(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("Unknown payment_source: totaltSygPaymentSource[23455384]"));
	}
	
	@Test
	public void ReservationOfFixedAmount_AddressVerification_ResultIsSuccess() throws Throwable 
	{
		PaymentReservationWithAddressRequest request = 
				new PaymentReservationWithAddressRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setCardholderName("cardholder name").setCardholderAddress("cardholder address").setIssueNumber("issue number").setStartMonth("start month")
		.setStartYear("start year").setSource("mobi").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("Success", result.getBody().getResult());
	}
	
	@Test
	public void ReservationOfFixedAmount_ExpiredCard_ReturnsExpiredStatus() throws Throwable 
	{
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		int currentMonth = now.get(Calendar.MONTH);
		String cardMonth = "";
		String cardYear = new Integer(currentYear).toString();

		if (currentMonth == 0)
		{
			cardMonth = "12";
			cardYear = new Integer(currentYear - 1).toString();
		}
		else
		{
			cardMonth = new Integer(currentMonth).toString();
		}

		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", cardMonth, cardYear).setCvc("111")).setAuthType(AuthType.subscription);
		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Expired", result.getBody().getTransactions().getTransaction().get(0).getCardStatus());

	}
	
	@Test
	public void ReservationOfFixedAmount_ValidCard_ReturnsValidStatus() throws Throwable 
	{
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		int currentMonth = now.get(Calendar.MONTH);
		String cardMonth = new Integer(currentMonth + 1).toString();
		String cardYear = new Integer(currentYear + 1).toString();

		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", cardMonth, cardYear).setCvc("111")).setAuthType(AuthType.subscription);
		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Valid", result.getBody().getTransactions().getTransaction().get(0).getCardStatus());
	}
	
	@Test
	public void ReservationOfFixedAmount_RecurringPayment_ReturnsAResult() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111")).setAuthType(AuthType.subscription);
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("Success", (String)result.getBody().getResult());
		assertFalse("".equals(result.getBody().getTransactions().toString()));
	}

	/*
	 * reservationOfFixedAmountAndCaptureActionTests
	 */

	@Test
	public void ReservationOfFixedAmountAndCaptureAction_CreatesATransaction_StatusIsCaptured() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111")).setAuthType(AuthType.paymentAndCapture);
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("captured", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}
	/*
	 * ReservationWith3dSecureTests
	 */
	@Test
	public void ReservationWith3dSecure_On3dSecure_ResultIs3dSecure() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		APIResponse result = api.reservationOfFixedAmount(request);
		
		assertEquals("3dSecure", result.getBody().getResult());
		assertEquals("WorkingPaReq", result.getBody().getPaReq());
		assertEquals("https://testbank.pensio.com/ThreeDSecure", result.getBody().getRedirectUrl());
	}

	/*
	 * ReservationAndCaptureWith3dSecureTests
	 */

	@Test
	public void ReservationAndCaptureWith3dSecure_On3dSecure_ResultIs3dSecure() throws Throwable 
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		APIResponse result = api.reservationOfFixedAmountAndCapture(request);
		
		assertEquals("3dSecure", result.getBody().getResult());
		assertEquals("WorkingPaReq", result.getBody().getPaReq());
		assertEquals("https://testbank.pensio.com/ThreeDSecure", result.getBody().getRedirectUrl());
	}

	/*
	 * Verify3DSecureReservationTests
	 */

	@Test
	public void Verify3DSecureReservation_AllParametersAreThere_ResultIsSucces() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(false);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");

		APIResponse result = api.verify3dSecure(request);
		
		assertEquals("Success", result.getBody().getResult());
	}
	
	@Test
	public void Verify3DSecureReservation_PaymentIdIsMissing_ResultIsFailed() throws Throwable
	{
		String missingID = ""; 
		Verify3dRequest request = new Verify3dRequest(missingID, "WorkingPaRes");
		String message = "";
		
		try
		{
			APIResponse result = api.verify3dSecure(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("The payment with id: " + missingID + " does not exist"));

	}

	@Test
	public void Verify3DSecureReservation_PaymentIdIsMalformed_ResultIsFailed() throws Throwable
	{
		String malformedID = "1a"; 
		Verify3dRequest request = new Verify3dRequest(malformedID, "WorkingPaRes");
		String message = "";
		
		try
		{
			APIResponse result = api.verify3dSecure(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("Invalid status for Payment"));
	}
	
	@Test
	public void Verify3DSecureReservation_3dSecureGoesWell_CorrectStatus() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(false);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");

		APIResponse result = api.verify3dSecure(request);
		
		assertEquals("preauth", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}
	
	@Test
	public void Verify3DSecureReservation_3dSecureWasNotVerified_ResultIsError() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(false);
		Verify3dRequest request = new Verify3dRequest(paymentId, "FailingPaRes");

		APIResponse result = api.verify3dSecure(request);
		
		assertEquals("Failed", result.getBody().getResult());
		assertEquals("3dsecure_failed", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());

	}
	
	/*
	 * Verify3DSecureReservationAndCaptureTests
	 */
	
	@Test
	public void Verify3DSecureReservationAndCapture_AllParametersAreThere_ResultIsSucces() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(true);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");
		
		APIResponse result = api.verify3dSecure(request);
		
		assertEquals("Success", result.getBody().getResult());
	}
	
	@Test
	public void Verify3DSecureReservationAndCapture_3dSecureGoesWell_CorrectStatus() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(true);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");
		
		APIResponse result = api.verify3dSecure(request);
		
		assertEquals("captured", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}
	
	@Test
	public void Verify3DSecureReservationAndCapture_3dSecureWasNotVerified_ResultIsError() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(true);
		Verify3dRequest request = new Verify3dRequest(paymentId, "FailingPaRes");
		
		APIResponse result = api.verify3dSecure(request);
		
		assertEquals("3dsecure_failed", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}


	/*
	 * ProcessorCustomerInfoTests
	 */

	@Test
	public void ProcessorCustomerInfo_CustomerInfoIsGiven_ResultIsSucces() throws Throwable
	{
		CustomerInfo customerInfo = commonCustomerInfo();
		customerInfo.setClientIp("192.168.13.37");
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		request.setCustomerInfo(customerInfo);
		
		APIResponse result = api.initiatePaymentRequest(request);
		
		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void ProcessorCustomerInfo_BadClientIP_Fails() throws Throwable
	{
		CustomerInfo customerInfo = commonCustomerInfo();
		
		String badClientIp = "999.999.999.999";
		customerInfo.setClientIp(badClientIp);
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		request.setCustomerInfo(customerInfo);
		String message = "";
		
		try
		{
			APIResponse result = api.initiatePaymentRequest(request);
		}
		catch (PensioAPIException ex)
		{
			message = ex.getMessage(); 
		}

		assertTrue(message.contains("Parameter customer_info[client_ip] was not a valid IPv4 (" + badClientIp + ")"));

	}
	
	private CustomerInfo commonCustomerInfo()
	{
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setEmail("e@mail.invalid");
		customerInfo.setCustomerPhone("800-i-got-money");
		customerInfo.setUsername("username");
		customerInfo.setBankName("Bank Rupt");
		customerInfo.setBankPhone("800-we-need-money");

		CustomerInfoAddress shippingAddress = new CustomerInfoAddress();
		shippingAddress.setFirstname("ship first name");
		shippingAddress.setLastname("ship last name");
		shippingAddress.setAddress("ship address");
		shippingAddress.setCity("ship city");
		shippingAddress.setRegion("ship region");
		shippingAddress.setPostal("shippostal");
		shippingAddress.setCountry("dk");
		customerInfo.setShippingAddress(shippingAddress);
		
		CustomerInfoAddress billingAddress = new CustomerInfoAddress();
		billingAddress.setFirstname("bil first name");
		billingAddress.setLastname("bil last name");
		billingAddress.setAddress("billing address");
		billingAddress.setCity("billing city");
		billingAddress.setRegion("billing region");
		billingAddress.setPostal("billpostal");
		billingAddress.setCountry("us");
		customerInfo.setBillingAddress(billingAddress);

		return customerInfo;
	}

	/*
	 * 
	 */
	private String getSecureStartedPaymentId(boolean capture) throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		request.setSource("eCommerce").setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		APIResponse result = null;
		
		if (capture)
		{
			result = api.reservationOfFixedAmountAndCapture(request);
		}
		else
		{
			result = api.reservationOfFixedAmount(request);
		}
		
		return result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
	}
	
	
}
