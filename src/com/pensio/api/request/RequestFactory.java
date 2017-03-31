package com.pensio.api.request;

import com.pensio.Amount;

public class RequestFactory
{
	public CaptureReservationRequest getCaptureRequest(String paymentId)
	{
		return new CaptureReservationRequest(paymentId);
	}
	
	public ChargeSubscriptionRequest getChargeRequest(String subscriptionId)
	{
		return new ChargeSubscriptionRequest(subscriptionId);
	}
	
	public PaymentRequest getPaymentRequest(String shopOrderId, String terminal, Amount amount)
	{
		return new PaymentRequest(shopOrderId, terminal, amount);
	}
	
	public ReserveSubscriptionChargeRequest getReserveSubscriptionChargeRequest(String subscriptionId)
	{
		return new ReserveSubscriptionChargeRequest(subscriptionId);
	}
	
	public RefundRequest getRefundRequest(String paymentId)
	{
		return new RefundRequest(paymentId);
	}
	
	public PaymentReservationRequest getPaymentReservationRequest(String shopOrderId, String terminal, Amount amount)
	{
		return new PaymentReservationRequest(shopOrderId, terminal, amount);
	}

	public Verify3dRequest getVerify3dRequest(String transactionId, String paRes)
	{
		return new Verify3dRequest(transactionId, paRes);
	}

	public TransactionsRequest getTransactionsRequest(String transactionId)
	{
		return new TransactionsRequest(transactionId);
	}

	public ReleaseReservationRequest getReleaseReservationRequest(String paymentId)
	{
		return new ReleaseReservationRequest(paymentId);
	}

	public FundingListRequest getFundingListRequest(int page)
	{
		return new FundingListRequest(page);
	}
	
}
