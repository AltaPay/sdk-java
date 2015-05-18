package com.pensio.api.request;

public class CreditCard 
{
	protected String token;
	protected String cardNumber;
	protected String expiryMonth;
	protected String expiryYear;
	protected String cvc;
	
	protected CreditCard()
	{
		
	}
	
	public static CreditCard get(String token)
	{
		CreditCard creditCard = new CreditCard();
		creditCard.token = token;
		return creditCard;
	}
	
	public static CreditCard get(String cardNumber, String expiryMonth, String expiryYear)
	{
		CreditCard creditCard = new CreditCard();
		creditCard.cardNumber = cardNumber;
		creditCard.expiryMonth = expiryMonth;
		creditCard.expiryYear = expiryYear;
		return creditCard;
	}

	public String getToken() 
	{
		return token;
	}

	public String getCardNumber() 
	{
		return cardNumber;
	}

	public String getExpiryMonth() 
	{
		return expiryMonth;
	}

	public String getExpiryYear() 
	{
		return expiryYear;
	}

	public String getCvc() 
	{
		return cvc;
	}
	
	public CreditCard setCvc(String cvc)
	{
		this.cvc = cvc;
		return this;
	}

}
