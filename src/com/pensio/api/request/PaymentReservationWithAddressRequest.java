package com.pensio.api.request;

import com.pensio.Amount;

public class PaymentReservationWithAddressRequest extends PaymentReservationRequest
{
	String cardholderName;
	String cardholderAddress;
	String issueNumber;
	String startMonth;
	String startYear;

	public PaymentReservationWithAddressRequest(String shopOrderId, String terminal, Amount amount)
	{
		super(shopOrderId, terminal, amount);
	}

	public String getCardholderName()
	{
		return cardholderName;
	}
	
	public PaymentReservationWithAddressRequest setCardholderName(String cardholderName)
	{
		this.cardholderName = cardholderName;
		return this;
	}
	
	public String getCardholderAddress()
	{
		return cardholderAddress;
	}
	
	public PaymentReservationWithAddressRequest setCardholderAddress(String cardholderAddress)
	{
		this.cardholderAddress = cardholderAddress;
		return this;
	}
	
	public String getIssueNumber()
	{
		return issueNumber;
	}
	
	public PaymentReservationWithAddressRequest setIssueNumber(String issueNumber)
	{
		this.issueNumber = issueNumber;
		return this;
	}
	
	public String getStartMonth()
	{
		return startMonth;
	}
	
	public PaymentReservationWithAddressRequest setStartMonth(String startMonth)
	{
		this.startMonth = startMonth;
		return this;
	}
	
	public String getStartYear()
	{
		return startYear;
	}
	
	public PaymentReservationWithAddressRequest setStartYear(String startYear)
	{
		this.startYear = startYear;
		return this;
	}
	
}
