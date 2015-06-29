package com.pensio.api.request;

import java.util.Date;

import com.pensio.Amount;

public class PaymentReservationRequest
	extends PaymentRequest
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
	
	@Override
	public PaymentReservationRequest setAmount(Amount amount) 
	{
		super.setAmount(amount);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setAuthType(AuthType authType)
	{
		super.setAuthType(authType);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setConfig(PaymentRequestConfig config)
	{
		super.setConfig(config);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setCookie(String cookie)
	{
		super.setCookie(cookie);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setCustomerCreateAt(Date customerCreateAt)
	{
		super.setCustomerCreateAt(customerCreateAt);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setCustomerInfo(CustomerInfo customerInfo)
	{
		super.setCustomerInfo(customerInfo);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setLanguage(String language)
	{
		super.setLanguage(language);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setShippingMethod(String shippingMethod)
	{
		super.setShippingMethod(shippingMethod);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setShopOrderId(String shopOrderId)
	{
		super.setShopOrderId(shopOrderId);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setTerminal(String terminal)
	{
		super.setTerminal(terminal);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setCreditCardToken(String creditCardToken)
	{
		super.setCreditCardToken(creditCardToken);
		return this;
	}
	
	@Override
	public PaymentReservationRequest addPaymentInfo(String key, String value)
	{
		super.addPaymentInfo(key, value);
		return this;
	}
	
	@Override
	public PaymentReservationRequest setUsePayPass(boolean usePayPass)
	{
		super.setUsePayPass(usePayPass);
		return this;
	}

	@Override
	public PaymentReservationRequest setGiftCardToken(String giftCardToken)
	{
		super.setGiftCardToken(giftCardToken);
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
