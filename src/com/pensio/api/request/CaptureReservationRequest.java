package com.pensio.api.request;

import com.pensio.Amount;

public class CaptureReservationRequest
{
	private String paymentId;				// Required
	private Amount amount;					// Optional
	private String reconciliationIdentifier;// Optional
	private String invoiceNumber;			// Optional
	private String salesTax;				// Optional
	private OrderLine[] orderLines;			// Optional
	
	public CaptureReservationRequest(String paymentId)
	{
		this.paymentId = paymentId;
		this.orderLines = new OrderLine[0];
	}

	public String getPaymentId()
	{
		return paymentId;
	}

    // Added as support to the old implementation to avoid an immediate action from any external party
    public String getPaymentId()
	{
		return transactionId;
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

	public OrderLine[] getOrderLines() 
	{
		return orderLines;
	}

	public CaptureReservationRequest setOrderLines(OrderLine[] orderLines) 
	{
		this.orderLines = orderLines;
		return this;
	}

	
}
