package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.RedirectResponseDataItem;
import com.pensio.api.request.AuthType;
import com.pensio.api.request.CreditCard;
import com.pensio.api.request.CustomerInfo;
import com.pensio.api.request.CustomerInfoAddress;
import com.pensio.api.request.PaymentReservationRequest;
import com.pensio.api.request.PaymentSource;
import com.pensio.api.request.Verify3dRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PensioProcessorAPITest extends PensioAPITestBase
{
	protected PensioProcessorAPI	api;

	@BeforeEach
	public void setUp()
			throws Exception, Throwable
	{
		String apiUrl = System.getProperty("pensio.TestUrl","https://testgateway.pensio.com/");
		String username = System.getProperty("pensio.TestApiUsername","shop api");
		String password = System.getProperty("pensio.TestApiPassword","testpassword");
		api = new PensioProcessorAPI(apiUrl, username, password);
	}

	/*
	 * ReservationOfFixedAmountActionTests
	 */
	@Test
	public void reservationOfFixedAmount_WithAllParameters_ResultIsSuccess() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111")).setSource(PaymentSource.eCommerce);

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void reservationOfFixedAmount_SomeParametersMissing_ThrowsException() throws Throwable
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

		assertTrue(message.contains("Either payment_request_id or amount,terminal,shop_orderid,payment_source and currency must be supplied"));
	}

	@Test
	public void reservationOfFixedAmount_ExpiryYearIsLessThan4Digits_ThrowsException() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "999").setCvc("111"));
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
	public void reservationOfFixedAmount_ExpiryYearIsMoreThan4Digits_ThrowsException() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "20125").setCvc("111"));
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
	public void reservationOfFixedAmount_ExpiryYearContainsAnythingButNumbers_ThrowsException() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "201A").setCvc("111"));
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
	public void reservationOfFixedAmount_CreateTransaction_StatusIsPreauth() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("preauth", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}


	@Test
	public void reservationOfFixedAmount_PaymentSourceSetToMoto_ResultIsSuccess() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.moto).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void reservationOfFixedAmount_PaymentSourceSetToMobi_ResultIsSuccess() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.mobi).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void reservationOfFixedAmount_WithAddress_ResultIsSuccess() throws Throwable
	{
		PaymentReservationRequest request =
				new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setCardholderName("cardholder name").setCardholderAddress("cardholder address").setIssueNumber("issue number").setStartMonth("start month")
				.setStartYear("start year").setSource(PaymentSource.mobi).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void reservationOfFixedAmount_ExpiredCard_StatusIsExpired() throws Throwable
	{
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		int currentMonth = now.get(Calendar.MONTH);
		String cardMonth = "";
		String cardYear =  String.valueOf((currentYear));
		if (currentMonth == 0)
		{
			cardMonth = "12";
			cardYear =  String.valueOf((currentYear - 1));
		}
		else
		{
			cardMonth =  String.valueOf((currentMonth));
		}
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", cardMonth, cardYear).setCvc("111"));

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Expired", result.getBody().getTransactions().getTransaction().get(0).getCardStatus());

	}

	@Test
	public void reservationOfFixedAmount_ValidCard_StatusIsValid() throws Throwable
	{
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		int currentMonth = now.get(Calendar.MONTH);
		String cardMonth = String.valueOf(currentMonth + 1);
		String cardYear =  String.valueOf((currentYear + 1));
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", cardMonth, cardYear).setCvc("111"));

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Valid", result.getBody().getTransactions().getTransaction().get(0).getCardStatus());
	}

	@Test
	public void reservationOfFixedAmount_RecurringPayment_ReturnsTransactions() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"))
				.setAuthType(AuthType.subscription);

		APIResponse result = api.initiatePaymentRequest(request);

		System.out.println("aaa"+result.getBody().getTransactions().toString()+"bbb");
		assertEquals("Success", (String)result.getBody().getResult());
		assertFalse("".equals(result.getBody().getTransactions().toString()));
	}

	/*
	 * reservationOfFixedAmountAndCaptureActionTests
	 */

	@Test
	public void reservationOfFixedAmountAndCaptureAction_CreateTransaction_StatusIsCaptured() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111")).setAuthType(AuthType.paymentAndCapture);

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("captured", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}
	/*
	 * ReservationWith3dSecureTests
	 */
	@ParameterizedTest
	@ValueSource(booleans = {true, false})
	public void reservationWith3dSecure_On3dSecure_ResultIs3dSecure(boolean callReservationOfFixedAmount) throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));

		APIResponse result = null;
		if (callReservationOfFixedAmount)
		{
			result = api.reservationOfFixedAmount(request);
		}
		else
		{
			result = api.reservation(request);
		}

		assertEquals("3dSecure", result.getBody().getResult());
		assertEquals("WorkingPaReq", getRedirectResponseDataItem(result, "PaReq").getValue());
		assertTrue(result.getBody().getRedirectResponse().getUrl().contains("://testbank."));
		assertTrue(result.getBody().getRedirectResponse().getUrl().contains("/ThreeDSecure"));
	}

	/*
	 * ReservationAndCaptureWith3dSecureTests
	 */

	@Test
	public void reservationAndCaptureWith3dSecure_On3dSecure_ResultIs3dSecure() throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));

		APIResponse result = api.reservationOfFixedAmountAndCapture(request);

		assertEquals("3dSecure", result.getBody().getResult());
		assertEquals("WorkingPaReq", getRedirectResponseDataItem(result, "PaReq").getValue());
		assertTrue(result.getBody().getRedirectResponse().getUrl().contains("://testbank."));
		assertTrue(result.getBody().getRedirectResponse().getUrl().contains("/ThreeDSecure"));
	}

	private RedirectResponseDataItem getRedirectResponseDataItem(APIResponse result, String key)
	{
		List<RedirectResponseDataItem> list = result.getBody().getRedirectResponse().getData().getItem();

		for (RedirectResponseDataItem item: list) {
			if (item.getKey().equals(key)) {
				return item;
			}
		}

		return null;
	}

	/*
	 * Verify3DSecureReservationTests
	 */

	@ParameterizedTest
	@ValueSource(booleans = {true, false})
	public void verify3DSecureReservation_WithAllParameters_ResultIsSuccess(boolean callReservationOfFixedAmount) throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(false, callReservationOfFixedAmount);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");

		APIResponse result = api.verify3dSecure(request);

		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void verify3DSecureReservation_PaymentIdIsMissing_ThrowsException() throws Throwable
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

		assertTrue(message.contains("Parameter 'transactionId' value is invalid."));

	}

	@Test
	public void verify3DSecureReservation_PaymentIdIsMalformed_ThrowsException() throws Throwable
	{
		String malformedID = "11a";
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

		assertTrue(message.contains("Parameter 'transactionId' value is invalid."));
	}

	@ParameterizedTest
	@ValueSource(booleans = {true, false})
	public void verify3DSecureReservation_3dSecureGoesWell_TransactionStatusIsPreauth(boolean callReservationOfFixedAmount) throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(false, callReservationOfFixedAmount);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");

		APIResponse result = api.verify3dSecure(request);

		assertEquals("preauth", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}

	@ParameterizedTest
	@ValueSource(booleans = {true, false})
	public void verify3DSecureReservation_3dSecureWasNotVerified_TransactionStatusIs3dSecure_Failed(boolean callReservationOfFixedAmount) throws Throwable
	{
		PaymentReservationRequest paymentRequest = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		paymentRequest.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4140000000000466", "12", "2025").setCvc("111"));

		APIResponse paymentResult = null;
		paymentResult = api.reservationOfFixedAmount(paymentRequest);
		String paymentId = paymentResult.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		Verify3dRequest request = new Verify3dRequest(paymentId, "FailingPaRes");

		APIResponse result = api.verify3dSecure(request);

		assertEquals("Failed", result.getBody().getResult());
		assertEquals("3dsecure_failed", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());

	}

	/*
	 * Verify3DSecureReservationAndCaptureTests
	 */

	@Test
	public void verify3DSecureReservationAndCapture_WithAllParameters_ResultIsSucces() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(true);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");

		APIResponse result = api.verify3dSecure(request);

		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void verify3DSecureReservationAndCapture_3dSecureGoesWell_TransactionStatusIsCaptured() throws Throwable
	{
		String paymentId = getSecureStartedPaymentId(true);
		Verify3dRequest request = new Verify3dRequest(paymentId, "WorkingPaRes");

		APIResponse result = api.verify3dSecure(request);

		assertEquals("captured", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}

	@Test
	public void verify3DSecureReservationAndCapture_3dSecureWasNotVerified_ResultIsError() throws Throwable
	{
		PaymentReservationRequest paymentRequest = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		paymentRequest.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4140000000000466", "12", "2025").setCvc("111"));

		APIResponse paymentResult = null;
		paymentResult = api.reservationOfFixedAmount(paymentRequest);
		paymentResult = api.reservation(paymentRequest);

		String paymentId = paymentResult.getBody().getTransactions().getTransaction().get(0).getTransactionId();

		Verify3dRequest request = new Verify3dRequest(paymentId, "FailingPaRes");

		APIResponse result = api.verify3dSecure(request);

		assertEquals("3dsecure_failed", result.getBody().getTransactions().getTransaction().get(0).getTransactionStatus());
	}


	/*
	 * ProcessorCustomerInfoTests
	 */
	@Test
	public void processorCustomerInfo_CustomerInfoIsGiven_ResultIsSucces() throws Throwable
	{
		CustomerInfo customerInfo = commonCustomerInfo();
		customerInfo.setClientIp("192.168.13.37");
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
		request.setCustomerInfo(customerInfo);

		APIResponse result = api.initiatePaymentRequest(request);

		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void processorCustomerInfo_BadClientIP_ThrowsException() throws Throwable
	{
		CustomerInfo customerInfo = commonCustomerInfo();

		String badClientIp = "999.999.999.999";
		customerInfo.setClientIp(badClientIp);
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), getTerminalName(), Amount.get(100.00, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));
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

		assertTrue(message.contains("Parameter customer_info[client_ip] was not a valid IPv4 or IPv6 ('" + badClientIp + "')"));

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

	private String getSecureStartedPaymentId(boolean capture) throws Throwable
	{
		return getSecureStartedPaymentId(capture, false);
	}

	/*
	 *
	 */
	private String getSecureStartedPaymentId(boolean capture, boolean callReservationOfFixedAmount) throws Throwable
	{
		PaymentReservationRequest request = new PaymentReservationRequest(getOrderId(), get3DSecureTerminalName(), Amount.get(5.68, Currency.EUR));
		request.setSource(PaymentSource.eCommerce).setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("111"));

		APIResponse result = null;

		if (capture)
		{
			result = api.reservationOfFixedAmountAndCapture(request);
		}
		else
		{
			if (callReservationOfFixedAmount)
			{
				result = api.reservationOfFixedAmount(request);
			}
			else
			{
				result = api.reservation(request);
			}
		}

		return result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
	}


}
