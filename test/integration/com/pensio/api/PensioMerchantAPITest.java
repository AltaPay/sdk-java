package com.pensio.api;

import static org.junit.Assert.*;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;


import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.AuthType;
import com.pensio.api.request.CaptureReservationRequest;
import com.pensio.api.request.ChargeSubscriptionRequest;
import com.pensio.api.request.CreditCard;
import com.pensio.api.request.FundingListRequest;
import com.pensio.api.request.MultiPaymentRequestChild;
import com.pensio.api.request.MultiPaymentRequestParent;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;
import com.pensio.api.request.RefundRequest;
import com.pensio.api.request.ReleaseReservationRequest;
import com.pensio.api.request.ReserveSubscriptionChargeRequest;

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
		PaymentRequestResponse result = api.createPaymentRequest(
			new PaymentRequest(getOrderId(), "Pensio Test Terminal", Amount.get(1.00, Currency.EUR))
		);
		
		assertNotNull(result.getUrl());
	}

	@Test
	public void createMotoReservation() throws Throwable 
	{
		APIResponse result = api.reservation(
			new PaymentReservationRequest(getOrderId(), "Pensio Test Terminal", Amount.get(1.00, Currency.EUR))
				.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
		);
		
		assertEquals("Success",result.getBody().getResult());
	}
	
	@Test
	public void captureReservation() throws Throwable 
	{
		String orderId = getOrderId();
		APIResponse result = api.reservation(
			new PaymentReservationRequest(orderId, "Pensio Test Terminal", Amount.get(3.00, Currency.EUR))
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
	
	@Test
	public void releaseReservation() throws Throwable 
	{
		APIResponse result = api.reservation(
			new PaymentReservationRequest(getOrderId(), "Pensio Test Terminal", Amount.get(3.00, Currency.EUR))
				.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
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
		String orderId = getOrderId();
		APIResponse result = api.reservation(
			new PaymentReservationRequest(orderId, "Pensio Test Terminal", Amount.get(3.00, Currency.EUR))
				.setAuthType(AuthType.paymentAndCapture)
				.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.refund(
			new RefundRequest(paymentId)
			.setAmount(Amount.get(2.00, Currency.EUR))
			.setReconciliationIdentifier(orderId)
		);
		
		assertEquals("Success",captureResult.getBody().getResult());
	}
	
	@Test
	public void chargeSubscription() throws Throwable 
	{
		String orderId = getOrderId();
		APIResponse result = api.reservation(
			new PaymentReservationRequest(orderId, "Pensio Test Terminal", Amount.get(3.00, Currency.EUR))
				.setAuthType(AuthType.subscription)
				.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.chargeSubscription(
			new ChargeSubscriptionRequest(paymentId)
			.setAmount(Amount.get(2.00, Currency.EUR))
			.setReconciliationIdentifier(orderId)
		);
		
		assertEquals("Success",captureResult.getBody().getResult());
	}

	@Test
	public void reserveSubscriptionCharge() throws Throwable 
	{
		String orderId = getOrderId();
		APIResponse result = api.reservation(
			new PaymentReservationRequest(orderId, "Pensio Test Terminal", Amount.get(3.00, Currency.EUR))
				.setAuthType(AuthType.subscription)
				.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
				
		);
		String paymentId = result.getBody().getTransactions().getTransaction().get(0).getTransactionId();
		APIResponse captureResult = api.reserveSubscriptionCharge(
			new ReserveSubscriptionChargeRequest(paymentId)
			.setAmount(Amount.get(2.00, Currency.EUR))
		);
		
		assertEquals("Success",captureResult.getBody().getResult());
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
		
		assertEquals("Pensio Functional Test Shop", fundingRecords.get(0).getShop());
	}
	
	@Test
	public void fraudCheckInCharge() throws Throwable 
	{
		String orderId = getOrderId();
		APIResponse result = api.reservation(
			new PaymentReservationRequest(orderId, "Pensio Red Test Terminal", Amount.get(3.00, Currency.EUR))
				.setAuthType(AuthType.payment)
				.setSource("eCommerce")
				.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"))
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
				new MultiPaymentRequestParent(orderId, "Pensio Test Terminal", Currency.EUR, multiPaymentRequestChildren) 
			);
		
		assertNotNull(result.getUrl());
	}
	
	private String getOrderId() throws Throwable
	{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		return "Test_"+Hex.encodeHexString(digest.digest(String.valueOf(System.currentTimeMillis()).getBytes()));
	}
}
