package com.pensio.api.request;

import java.util.Date;

import com.pensio.Amount;

public class PaymentRequest
{
	protected String shopOrderId;
	protected String terminal;
	protected AuthType authType;
	protected String language;
	protected String creditCardToken;
	protected Amount amount;
	protected String cookie;
	protected String shippingMethod;
	protected Date customerCreateAt;
	private boolean usePayPass;
	
	protected PaymentRequestConfig config;
	protected CustomerInfo customerInfo;
	private PaymentInfos paymentInfos;

	public PaymentRequest(String shopOrderId, String terminal, Amount amount)
	{
		this.shopOrderId = shopOrderId;
		this.terminal = terminal;
		this.amount = amount;
		paymentInfos = new PaymentInfos();
	}

	public String getShopOrderId()
	{
		return shopOrderId;
	}

	public PaymentRequest setShopOrderId(String shopOrderId)
	{
		this.shopOrderId = shopOrderId;
		return this;
	}

	public String getTerminal()
	{
		return terminal;
	}

	public PaymentRequest setTerminal(String terminal)
	{
		this.terminal = terminal;
		return this;
	}

	public AuthType getAuthType()
	{
		return authType;
	}

	public PaymentRequest setAuthType(AuthType authType)
	{
		this.authType = authType;
		return this;
	}

	public String getLanguage()
	{
		return language;
	}

	public PaymentRequest setLanguage(String language)
	{
		this.language = language;
		return this;
	}

	public String getCreditCardToken()
	{
		return creditCardToken;
	}

	public PaymentRequest setCreditCardToken(String creditCardToken)
	{
		this.creditCardToken = creditCardToken;
		return this;
	}

	public Amount getAmount()
	{
		return amount;
	}

	public PaymentRequest setAmount(Amount amount)
	{
		this.amount = amount;
		return this;
	}

	public String getCookie()
	{
		return cookie;
	}

	public PaymentRequest setCookie(String cookie)
	{
		this.cookie = cookie;
		return this;
	}

	public String getShippingMethod()
	{
		return shippingMethod;
	}

	public PaymentRequest setShippingMethod(String shippingMethod)
	{
		this.shippingMethod = shippingMethod;
		return this;
	}

	public Date getCustomerCreateAt()
	{
		return customerCreateAt;
	}

	public PaymentRequest setCustomerCreateAt(Date customerCreateAt)
	{
		this.customerCreateAt = customerCreateAt;
		return this;
	}

	public PaymentRequestConfig getConfig()
	{
		return config;
	}

	public PaymentRequest setConfig(PaymentRequestConfig config)
	{
		this.config = config;
		return this;
	}

	public CustomerInfo getCustomerInfo()
	{
		return customerInfo;
	}

	public PaymentRequest setCustomerInfo(CustomerInfo customerInfo)
	{
		this.customerInfo = customerInfo;
		return this;
	}

	public PaymentInfos getPaymentInfos()
	{
		return paymentInfos;
	}
	
	public PaymentRequest addPaymentInfo(String key, String value)
	{
		paymentInfos.add(key, value);
		return this;
	}

	public boolean getUsePayPass()
	{
		return usePayPass;
	}

	public PaymentRequest setUsePayPass(boolean usePayPass)
	{
		this.usePayPass = usePayPass;
		return this;
	}
}
