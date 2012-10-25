package request;

import java.util.Date;

import com.pensio.Amount;

public class PaymentReservationRequest
	extends PaymentRequest
{
	protected CreditCard creditCard;
	protected String source;

	public CreditCard getCreditCard() 
	{
		return creditCard;
	}

	public PaymentReservationRequest setCreditCard(CreditCard creditCard) 
	{
		this.creditCard = creditCard;
		return this;
	}

	public String getSource() 
	{
		return source;
	}

	public PaymentReservationRequest setSource(String source) 
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
	public PaymentReservationRequest setAuthType(String authType)
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
}
