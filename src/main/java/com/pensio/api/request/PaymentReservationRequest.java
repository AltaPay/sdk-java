package com.pensio.api.request;

import com.pensio.Amount;

public class PaymentReservationRequest
	extends PaymentRequest<PaymentReservationRequest>
{
	public PaymentReservationRequest(String shopOrderId, String terminal, Amount amount)
	{
		super(shopOrderId, terminal, amount);
	}

	public PaymentReservationRequest(String paymentRequestId)
	{
		this.paymentRequestId = paymentRequestId;
	}

	protected PaymentSource source;
	protected CreditCard creditCard;
	protected GiftCard giftCard;
	protected String cardholderName;
	protected String cardholderAddress;
	protected String issueNumber;
	protected String startMonth;
	protected String startYear;
	protected String paymentRequestId;

	public PaymentSource getSource() 
	{
		return source;
	}

	public PaymentReservationRequest setSource(String source)
	{
		return setSource(PaymentSource.valueOf(source));
	}

	public PaymentReservationRequest setSource(PaymentSource source)
	{
		this.source = source;
		return this;
	}

	public String getCardholderName()
	{
		return cardholderName;
	}

	public PaymentReservationRequest setCardholderName(String cardholderName)
	{
		this.cardholderName = cardholderName;
		return this;
	}

	public String getCardholderAddress()
	{
		return cardholderAddress;
	}

	public PaymentReservationRequest setCardholderAddress(String cardholderAddress)
	{
		this.cardholderAddress = cardholderAddress;
		return this;
	}

	public String getIssueNumber()
	{
		return issueNumber;
	}

	public PaymentReservationRequest setIssueNumber(String issueNumber)
	{
		this.issueNumber = issueNumber;
		return this;
	}

	public String getStartMonth()
	{
		return startMonth;
	}

	public PaymentReservationRequest setStartMonth(String startMonth)
	{
		this.startMonth = startMonth;
		return this;
	}

	public String getStartYear()
	{
		return startYear;
	}

	public PaymentReservationRequest setStartYear(String startYear)
	{
		this.startYear = startYear;
		return this;
	}


	public CreditCard getCreditCard()
	{
		return creditCard;
	}

	public PaymentReservationRequest setCreditCard(CreditCard creditCard)
	{
		this.creditCard = creditCard;
		return this;
	}


	public GiftCard getGiftCard()
	{
		return giftCard;
	}

	public PaymentReservationRequest setGiftCard(GiftCard giftCard)
	{
		this.giftCard = giftCard;
		return this;
	}
	
	public String getPaymentRequestId()
	{
		return paymentRequestId;
	}

	public void setPaymentRequestId(String paymentRequestId)
	{
		this.paymentRequestId = paymentRequestId;
	}

}
