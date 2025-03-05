package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Transaction;
import com.pensio.api.request.AgreementConfig;
import com.pensio.api.request.AgreementType;
import com.pensio.api.request.AgreementUnscheduledType;
import com.pensio.api.request.AuthType;
import com.pensio.api.request.CaptureReservationRequest;
import com.pensio.api.request.ChargeSubscriptionRequest;
import com.pensio.api.request.CreateInvoiceReservationRequest;
import com.pensio.api.request.CreditCard;
import com.pensio.api.request.CustomerInfo;
import com.pensio.api.request.CustomerInfoAddress;
import com.pensio.api.request.FraudService;
import com.pensio.api.request.FundingListRequest;
import com.pensio.api.request.MultiPaymentRequestChild;
import com.pensio.api.request.MultiPaymentRequestParent;
import com.pensio.api.request.OrderLine;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;
import com.pensio.api.request.PaymentSource;
import com.pensio.api.request.RefundRequest;
import com.pensio.api.request.ReleaseReservationRequest;
import com.pensio.api.request.ReserveSubscriptionChargeRequest;
import com.pensio.api.request.TransactionsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PensioMerchantAPITest extends PensioAPITestBase
{
	private PensioMerchantAPI api;

	@BeforeEach
	public void setUp()
			throws Exception
	{
		String apiUrl = System.getProperty("pensio.TestUrl", "https://testgateway.pensio.com/");
		String username = System.getProperty("pensio.TestApiUsername", "shop api");
		String password = System.getProperty("pensio.TestApiPassword", "testpassword");
		api = new PensioMerchantAPI(apiUrl, username, password);
	}

	@Test
	public void createPaymentRequest() throws Throwable
	{
		PaymentRequestResponse result = api.createPaymentRequest(
				new PaymentRequest(getOrderId(), "AltaPay Test Terminal", Amount.get(1.00, Currency.EUR))
		);

		assertNotNull(result.getUrl());
	}

	@Test
	public void createPaymentRequestWithAgreement() throws Throwable
	{
		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.unscheduled);
		agreementConfig.setAgreementUnscheduledType(AgreementUnscheduledType.incremental);

		PaymentRequestResponse result = api.createPaymentRequest(
				new PaymentRequest(getOrderId(), "AltaPay Test Terminal", Amount.get(7.77, Currency.EUR))
						.setAgreementConfig(agreementConfig)
		);

		assertNotNull(result.getUrl());
	}

	@Test
	public void createMotoReservation() throws Throwable
	{
		APIResponse result = api.reservation(
				new PaymentReservationRequest(getOrderId(), "AltaPay Test Terminal", Amount.get(1.00, Currency.EUR))
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))
		);

		assertEquals("Success",result.getBody().getResult());
	}

	@Test
	public void captureReservation() throws Throwable
	{
		OrderLine[] orderLines = new OrderLine[]{
				new OrderLine("realultimatepower", "KungFuBoy", 1.123, 2.234)
				,new OrderLine("nolikepirates", "Ninjaman", 4.456, 5.456)
		};
		orderLines[0].setTaxAmount(3000);
		orderLines[0].setTaxPercent(50);
		String orderId = getOrderId();
		System.out.println(orderId);
		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Test Terminal", Amount.get(3.00, Currency.EUR))
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.capture(
				new CaptureReservationRequest(paymentId)
						.setAmount(Amount.get(2.00, Currency.EUR))
						.setInvoiceNumber(orderId)
						.setReconciliationIdentifier(orderId)
						.setSalesTax("0.5")
						.setOrderLines(orderLines)
		);

		assertEquals("Success",captureResult.getBody().getResult());
	}

	@Test
	public void releaseReservation() throws Throwable
	{
		APIResponse result = api.reservation(
				new PaymentReservationRequest(getOrderId(), "AltaPay Test Terminal", Amount.get(3.00, Currency.EUR))
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.release(
				new ReleaseReservationRequest(paymentId)
		);

		assertEquals("Success",captureResult.getBody().getResult());
	}

	@Test
	public void refundReservation() throws Throwable
	{
		OrderLine[] orderLines = new OrderLine[]{
				new OrderLine("realultimatepower", "KungFuBoy", 1.123, 2.234)
				,new OrderLine("nolikepirates", "Ninjaman", 4.456, 5.456)
		};

		String orderId = getOrderId();
		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Test Terminal", Amount.get(3.00, Currency.EUR))
						.setAuthType(AuthType.paymentAndCapture)
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.refund(
				new RefundRequest(paymentId)
						.setAmount(Amount.get(2.00, Currency.EUR))
						.setReconciliationIdentifier(orderId)
						.setOrderLines(orderLines)
		);

		assertEquals("Success",captureResult.getBody().getResult());
	}

	@Test
	public void chargeSubscription() throws Throwable
	{
		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.recurring);


		String orderId = getOrderId();
		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Test Terminal", Amount.get(3.00, Currency.EUR))
						.setAuthType(AuthType.subscription)
						.setAgreementConfig(agreementConfig)
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.chargeSubscription(
				new ChargeSubscriptionRequest(paymentId)
						.setAmount(Amount.get(2.00, Currency.EUR))
						.setReconciliationIdentifier(orderId)
						.addPaymentInfo("description", "Recurring Agreement")
						.setCallbackOk("http://example.com/callback/ok")
						.setCallbackFail("http://example.com/callback/fail")
		);

		assertEquals("Success",captureResult.getBody().getResult());
		assertEquals("description",captureResult.getBody().getTransactions().getTransaction().get(1).getPaymentInfos().getPaymentInfo().get(0).getName());
		assertEquals("Recurring Agreement",captureResult.getBody().getTransactions().getTransaction().get(1).getPaymentInfos().getPaymentInfo().get(0).getValue());
	}

	@Test
	public void chargeSubscriptionWithUnscheduledAgreement() throws Throwable
	{
		String orderId = getOrderId();
		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.unscheduled);
		agreementConfig.setAgreementUnscheduledType(AgreementUnscheduledType.incremental);

		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Test Terminal", Amount.get(7.77, Currency.EUR))
						.setAuthType(AuthType.subscription)
						.setAgreementConfig(agreementConfig)
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("123"))
		);
		String agreementId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.chargeSubscription(
				new ChargeSubscriptionRequest(agreementId)
						.setAmount(Amount.get(7.00, Currency.EUR))
						.setReconciliationIdentifier(orderId)
						.setAgreementUnscheduledType(AgreementUnscheduledType.incremental)
		);

		assertEquals("Success",captureResult.getBody().getResult());
	}

	@Test
	public void reserveSubscriptionCharge() throws Throwable
	{
		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.recurring);

		String orderId = getOrderId();
		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Test Terminal", Amount.get(3.00, Currency.EUR))
						.setAuthType(AuthType.subscription)
						.setAgreementConfig(agreementConfig)
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))

		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.reserveSubscriptionCharge(
				new ReserveSubscriptionChargeRequest(paymentId)
						.setAmount(Amount.get(2.00, Currency.EUR))
		);

		assertEquals("Success",captureResult.getBody().getResult());
	}

	@Test
	public void reserveSubscriptionChargeWithUnscheduledAgreement() throws Throwable
	{
		String orderId = getOrderId();
		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.unscheduled);
		agreementConfig.setAgreementUnscheduledType(AgreementUnscheduledType.incremental);

		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Test Terminal", Amount.get(3.00, Currency.EUR))
						.setAuthType(AuthType.subscription)
						.setAgreementConfig(agreementConfig)
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2025").setCvc("123"))

		);
		String agreementId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.reserveSubscriptionCharge(
				new ReserveSubscriptionChargeRequest(agreementId)
						.setAmount(Amount.get(2.00, Currency.EUR))
						.setAgreementUnscheduledType(AgreementUnscheduledType.incremental)
						.addPaymentInfo("description", "Unscheduled Incremental Agreement")
						.setCallbackOk("http://example.com/callback/ok")
						.setCallbackFail("http://example.com/callback/fail")
		);

		assertEquals("Success",captureResult.getBody().getResult());
		assertEquals("description",captureResult.getBody().getTransactions().getTransaction().get(1).getPaymentInfos().getPaymentInfo().get(0).getName());
		assertEquals("Unscheduled Incremental Agreement",captureResult.getBody().getTransactions().getTransaction().get(1).getPaymentInfos().getPaymentInfo().get(0).getValue());
	}

	@Test
	public void getTerminalsTest() throws Throwable
	{
		APIResponse result = api.getTerminals();


		assertEquals("Success",result.getBody().getResult());
		assertNotNull(result.getBody().getTerminals());
		assertTrue(result.getBody().getTerminals().getTerminal().size() > 0);
	}

	@Test
	public void getTransactionsTest() throws Throwable
	{
		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.recurring);

		String orderId = getOrderId();
		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Test Terminal", Amount.get(3.00, Currency.EUR))
						.setAuthType(AuthType.subscription)
						.setAgreementConfig(agreementConfig)
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))

		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();

		APIResponse transactionsResult = api.transactions(new TransactionsRequest(paymentId));

		assertNotNull(transactionsResult.getBody().getTransactions());
		assertEquals(1, transactionsResult.getBody().getTransactions().getTransaction().size());
	}

	@Test
	public void fundingListTest() throws Throwable
	{
		APIResponse result = api.fundingList(new FundingListRequest());
		APIResponse currentResult = result;
		assertNotNull(result.getBody().getFundings());

		int page = 0;
		while(++page < result.getBody().getFundings().getNumberOfPages())
		{
			APIResponse extraResult = api.fundingList(new FundingListRequest(page));
			assertTrue(extraResult.getBody().getFundings().getFunding().size() > 0);
			currentResult = extraResult;
		}
		assertEquals("FunctionalTestContractID",currentResult.getBody().getFundings().getFunding().get(currentResult.getBody().getFundings().getFunding().size()-1).getContractIdentifier());
	}

	@Test
	public void fundingDownloadTest() throws Throwable
	{
		APIResponse result = api.fundingList(new FundingListRequest());
		APIResponse currentResult = result;
		assertNotNull(result.getBody().getFundings());

		int page = 0;
		while(++page < result.getBody().getFundings().getNumberOfPages())
		{
			APIResponse extraResult = api.fundingList(new FundingListRequest(page));
			assertTrue(extraResult.getBody().getFundings().getFunding().size() > 0);
			currentResult = extraResult;
		}
		String downloadLink = currentResult.getBody().getFundings().getFunding().get(currentResult.getBody().getFundings().getFunding().size()-1).getDownloadLink();

		List<FundingRecord> fundingRecords = api.downloadFunding(downloadLink);

		assertEquals("AltaPay Functional Test Shop", fundingRecords.get(0).getShop());
	}


	@Test
	public void fraudCheckInCharge() throws Throwable
	{
		String orderId = getOrderId();
		APIResponse result = api.reservation(
				new PaymentReservationRequest(orderId, "AltaPay Red Test Terminal", Amount.get(3.00, Currency.EUR))
						.setAuthType(AuthType.payment)
						.setSource(PaymentSource.eCommerce)
						.setCreditCard(CreditCard.get("4111111111111111", "12", "2029").setCvc("123"))
						.addPaymentInfo("fraudCheckTest", "Checkit!")

		);

		assertEquals("fraudCheckTest",result.getBody().getTransactions().getTransaction().get(0).getPaymentInfos().getPaymentInfo().get(0).getName());
		assertEquals("Checkit!",result.getBody().getTransactions().getTransaction().get(0).getPaymentInfos().getPaymentInfo().get(0).getValue());
	}

	@Test
	public void multiPaymentRequestCheck() throws Throwable
	{
		String orderId = getOrderId();
		List<MultiPaymentRequestChild> multiPaymentRequestChildren = new ArrayList<MultiPaymentRequestChild>();
		multiPaymentRequestChildren.add(new MultiPaymentRequestChild(3.00));
		multiPaymentRequestChildren.add(new MultiPaymentRequestChild(2.55));

		PaymentRequestResponse result = api.createMultiPaymentRequest(
				new MultiPaymentRequestParent(orderId, "AltaPay Test Terminal", Currency.EUR, multiPaymentRequestChildren)
		);

		assertNotNull(result.getUrl());
	}

	@Test
	public void createInvoiceReservation() throws Throwable
	{
		String orderId = getOrderId();
		APIResponse result = api.createInvoiceReservation(
				new CreateInvoiceReservationRequest(orderId, "AltaPay Test Invoice Terminal DK", Amount.get(3.00, Currency.DKK))
						.setCustomerInfo(
								new CustomerInfo().setBillingAddress(
												new CustomerInfoAddress()
														.setAddress("some street")
														.setCity("Some city")
														.setCountry("DK")
														.setFirstname("first name")
														.setLastname("last name")
														.setPostal("1234")
										)
										.setClientIp("127.0.0.1")
						)
						.setOrderLines(Arrays.asList(
								new OrderLine("description 1", "itemId", 12.12, 13.13)
								, new OrderLine("description 2", "itemId 2", 1, 10)
						))
						.setOrganisationNumber("12345678")
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.capture(
				new CaptureReservationRequest(paymentId)
		);

		assertEquals("Success",captureResult.getBody().getResult());
	}

	@Test
	public void createSimpleInvoiceReservation() throws Throwable
	{
		String orderId = getOrderId();

		CreateInvoiceReservationRequest request = new CreateInvoiceReservationRequest(orderId, "AltaPay Test Invoice Terminal DK", Amount.get(333.55, Currency.DKK));

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setEmail("customer email");
		customerInfo.setBillingAddress(new CustomerInfoAddress().setAddress("some address").setPostal("postal code 1234"));

		request.setCustomerInfo(customerInfo);

		APIResponse result = api.createInvoiceReservation(request);

		Transaction transaction = result.getBody().getTransactions().getTransaction().get(0);

		assertEquals(request.getTerminal(), transaction.getTerminal());
		assertEquals(request.getShopOrderId(), transaction.getShopOrderId());
		assertEquals(request.getCustomerInfo().getBillingAddress().getAddress(), transaction.getCustomerInfo().getBillingAddress().getAddress());
		assertEquals(request.getCustomerInfo().getBillingAddress().getPostal(), transaction.getCustomerInfo().getBillingAddress().getPostalCode());
		assertEquals(request.getCustomerInfo().getEmail(), transaction.getCustomerInfo().getEmail());

		assertNull(result.getBody().getMerchantErrorMessage());
		assertNull(result.getBody().getCardHolderErrorMessage());
		assertEquals("Success", result.getBody().getResult());
	}

	@Test
	public void createComplexInvoiceReservation() throws Throwable
	{
		String orderId = getOrderId();

		CreateInvoiceReservationRequest request = new CreateInvoiceReservationRequest(orderId, "AltaPay Test Invoice Terminal DK", Amount.get(777.99, Currency.DKK));

		request.addPaymentInfo("auxinfo1", "auxvalue1");

		request.setAuthType(AuthType.payment);
		request.setAccountNumber("111");
		request.setBankCode("222");
		request.setFraudService(FraudService.Maxmind);
		request.setPaymentSource(PaymentSource.eCommerce);
		request.setOrganisationNumber("333");
		request.setPersonalIdentifyNumber("444");
		request.setBirthDate(new GregorianCalendar(2016, 1, 1).getTime());

		OrderLine ol = new OrderLine("desc", "id", 1, 2);
		ol.setTaxPercent(10);
		ol.setUnitCode("code");
		ol.setDiscount(99.0);
		ol.setGoodsType("Item");
		request.setOrderLines(Arrays.asList(ol));

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setEmail("customer email");
		customerInfo.setUsername("user");
		customerInfo.setCustomerPhone("phone");
		customerInfo.setBankName("bank name");
		customerInfo.setBankPhone("bank phone");
		customerInfo.setAccountIdentifier("account identifier");

		CustomerInfoAddress billingAddress = new CustomerInfoAddress();
		billingAddress.setAddress("some address");
		billingAddress.setCity("city");
		billingAddress.setCountry("US");
		billingAddress.setFirstname("first");
		billingAddress.setLastname("last");
		billingAddress.setRegion("region");
		billingAddress.setPostal("postal code 1234");
		customerInfo.setBillingAddress(billingAddress);

		CustomerInfoAddress shippingAddress = new CustomerInfoAddress();
		shippingAddress.setAddress("ship some address");
		shippingAddress.setCity("ship city");
		shippingAddress.setCountry("BR");
		shippingAddress.setFirstname("ship first");
		shippingAddress.setLastname("ship last");
		shippingAddress.setRegion("ship region");
		shippingAddress.setPostal("ship postal code 1234");
		customerInfo.setShippingAddress(shippingAddress);

		CustomerInfo recipientInfo = new CustomerInfo();
		recipientInfo.setEmail("customer email2");
		recipientInfo.setUsername("user2");
		recipientInfo.setCustomerPhone("phone2");
		recipientInfo.setBankName("bank name2");
		recipientInfo.setBankPhone("bank phone2");
		recipientInfo.setAccountIdentifier("account identifier2");

		CustomerInfoAddress recipientAddress = new CustomerInfoAddress();
		recipientAddress.setAddress("some address2");
		recipientAddress.setCity("city2");
		recipientAddress.setCountry("US");
		recipientAddress.setFirstname("first2");
		recipientAddress.setLastname("last2");
		recipientAddress.setRegion("region2");
		recipientAddress.setPostal("postal code 12345");
		recipientInfo.setBillingAddress(recipientAddress);

		request.setCustomerInfo(customerInfo);
		request.setRecipientInfo(recipientInfo);

		APIResponse result = api.createInvoiceReservation(request);

		Transaction transaction = result.getBody().getTransactions().getTransaction().get(0);

		assertEquals(request.getTerminal(), transaction.getTerminal());
		assertEquals(request.getShopOrderId(), transaction.getShopOrderId());

		assertEquals(request.getAuthType().toString(), transaction.getAuthType());

		assertEquals("auxinfo1", transaction.getPaymentInfos().getPaymentInfo().get(0).getName());
		assertEquals(request.getPaymentInfos().get("auxinfo1").getValue(), transaction.getPaymentInfos().getPaymentInfo().get(0).getValue());

		assertCustomerInfo(request.getCustomerInfo(), transaction.getCustomerInfo());
		assertCustomerInfo(request.getRecipientInfo(), transaction.getRecipientInfo());

		assertNull(result.getBody().getMerchantErrorMessage());
		assertNull(result.getBody().getCardHolderErrorMessage());
		assertEquals("Success", result.getBody().getResult());
	}

	private static void assertCustomerInfo(CustomerInfo ciReq, com.pensio.api.generated.CustomerInfo ci) {
		assertEquals(ciReq.getEmail(), ci.getEmail());
		assertEquals(ciReq.getUsername(), ci.getUsername());
		assertEquals(ciReq.getCustomerPhone(), ci.getCustomerPhone());
		assertEquals(ciReq.getAccountIdentifier(), ci.getAccountIdentifier());

		assertEquals(ciReq.getBillingAddress().getFirstname(), ci.getBillingAddress().getFirstname());
		assertEquals(ciReq.getBillingAddress().getLastname(), ci.getBillingAddress().getLastname());
		assertEquals(ciReq.getBillingAddress().getAddress(), ci.getBillingAddress().getAddress());
		assertEquals(ciReq.getBillingAddress().getCity(), ci.getBillingAddress().getCity());
		assertEquals(ciReq.getBillingAddress().getRegion(), ci.getBillingAddress().getRegion());
		assertEquals(ciReq.getBillingAddress().getPostal(), ci.getBillingAddress().getPostalCode());
		assertEquals(ciReq.getBillingAddress().getCountry(), ci.getBillingAddress().getCountry());

		assertEquals(ciReq.getShippingAddress().getFirstname(), ci.getShippingAddress().getFirstname());
		assertEquals(ciReq.getShippingAddress().getLastname(), ci.getShippingAddress().getLastname());
		assertEquals(ciReq.getShippingAddress().getAddress(), ci.getShippingAddress().getAddress());
		assertEquals(ciReq.getShippingAddress().getCity(), ci.getShippingAddress().getCity());
		assertEquals(ciReq.getShippingAddress().getRegion(), ci.getShippingAddress().getRegion());
		assertEquals(ciReq.getShippingAddress().getPostal(), ci.getShippingAddress().getPostalCode());
		assertEquals(ciReq.getShippingAddress().getCountry(), ci.getShippingAddress().getCountry());
	}
}
