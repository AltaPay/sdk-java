package com.pensio.api.request;

import com.pensio.Amount;

public class RefundRequest
{
	private String paymentId;
	private Amount amount;
	private String reconciliationIdentifier;
	private OrderLine[] orderLines;
	
	public RefundRequest(String paymentId)
	{
		this.paymentId = paymentId;
		setOrderLines(new OrderLine[0]);
	}

	public String getPaymentId()
	{
		return paymentId;
	}

	public Amount getAmount()
	{
		return amount;
	}
	
	public String getAmountString()
	{
		if(amount == null)
		{
			return null;
		}
		return amount.getAmountString();
	}

	public RefundRequest setAmount(Amount amount)
	{
		this.amount = amount;
		return this;
	}

	public String getReconciliationIdentifier()
	{
		return reconciliationIdentifier;
	}

	public RefundRequest setReconciliationIdentifier(String reconciliationIdentifier)
	{
		this.reconciliationIdentifier = reconciliationIdentifier;
		return this;
	}

	public OrderLine[] getOrderLines() {
		return orderLines;
	}

	public RefundRequest setOrderLines(OrderLine[] orderLines) {
		this.orderLines = orderLines;
		return this;
	}

	
}
