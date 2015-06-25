package com.pensio.api.request;

public class TransactionsRequest
{
	private String paymentId;

	public TransactionsRequest(String paymentId)
	{
		this.paymentId = paymentId;
	}

	public String getPaymentId() {
		return paymentId;
	}
}
