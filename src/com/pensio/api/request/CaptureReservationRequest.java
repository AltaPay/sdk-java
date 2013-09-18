package com.pensio.api.request;

import com.pensio.Amount;

public class CaptureReservationRequest
{
	private String paymentId;
	private Amount amount;
	private String reconciliationIdentifier;
	private String invoiceNumber;
	private String salesTax;
	
	public CaptureReservationRequest(String paymentId)
	{
		this.paymentId = paymentId;
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

	public CaptureReservationRequest setAmount(Amount amount)
	{
		this.amount = amount;
		return this;
	}

	public String getReconciliationIdentifier()
	{
		return reconciliationIdentifier;
	}

	public CaptureReservationRequest setReconciliationIdentifier(String reconciliationIdentifier)
	{
		this.reconciliationIdentifier = reconciliationIdentifier;
		return this;
	}

	public String getInvoiceNumber()
	{
		return invoiceNumber;
	}

	public CaptureReservationRequest setInvoiceNumber(String invoiceNumber)
	{
		this.invoiceNumber = invoiceNumber;
		return this;
	}

	public String getSalesTax()
	{
		return salesTax;
	}

	public CaptureReservationRequest setSalesTax(String salesTax)
	{
		this.salesTax = salesTax;
		return this;
	}

	
}
