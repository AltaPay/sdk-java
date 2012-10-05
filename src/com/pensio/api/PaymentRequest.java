package com.pensio.api;

import java.util.Date;

public class PaymentRequest
{
	private String shopOrderId;
	private String terminal;
	private String currency;
	private String authType;
	private String language;
	private String creditCardToken;
	private String amount;
	private String cookie;
	private String shippingMethod;
	private Date customerCreateAt;
	
	private PaymentRequestConfig config;
	private CustomerInfo customerInfo;

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

	public String getCurrency()
	{
		return currency;
	}

	public PaymentRequest setCurrency(String currency)
	{
		this.currency = currency;
		return this;
	}

	public String getAuthType()
	{
		return authType;
	}

	public PaymentRequest setAuthType(String authType)
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

	public String getAmount()
	{
		return amount;
	}

	public PaymentRequest setAmount(String amount)
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
	
}
