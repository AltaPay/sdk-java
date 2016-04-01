package com.pensio.api.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pensio.Amount;

public class PaymentRequest<T extends PaymentRequest<T>>
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
	private String giftCardToken;
	
	protected PaymentRequestConfig config;
	protected CustomerInfo customerInfo;
	private PaymentInfos paymentInfos = new PaymentInfos();
	private List<OrderLine> orderLines;

	public PaymentRequest()
	{

	}

	public PaymentRequest(String shopOrderId, String terminal, Amount amount)
	{
		this.shopOrderId = shopOrderId;
		this.terminal = terminal;
		this.amount = amount;
	}

	public String getShopOrderId()
	{
		return shopOrderId;
	}

	@SuppressWarnings("unchecked")
	public T setShopOrderId(String shopOrderId)
	{
		this.shopOrderId = shopOrderId;
		return (T)this;
	}

	public String getTerminal()
	{
		return terminal;
	}

	@SuppressWarnings("unchecked")
	public T setTerminal(String terminal)
	{
		this.terminal = terminal;
		return (T)this;
	}

	public AuthType getAuthType()
	{
		return authType;
	}

	@SuppressWarnings("unchecked")
	public T setAuthType(AuthType authType)
	{
		this.authType = authType;
		return (T)this;
	}

	public String getLanguage()
	{
		return language;
	}

	@SuppressWarnings("unchecked")
	public T setLanguage(String language)
	{
		this.language = language;
		return (T)this;
	}

	public String getCreditCardToken()
	{
		return creditCardToken;
	}

	@SuppressWarnings("unchecked")
	public T setCreditCardToken(String creditCardToken)
	{
		this.creditCardToken = creditCardToken;
		return (T)this;
	}

	public Amount getAmount()
	{
		return amount;
	}

	@SuppressWarnings("unchecked")
	public T setAmount(Amount amount)
	{
		this.amount = amount;
		return (T)this;
	}

	public String getCookie()
	{
		return cookie;
	}

	@SuppressWarnings("unchecked")
	public T setCookie(String cookie)
	{
		this.cookie = cookie;
		return (T)this;
	}

	public String getShippingMethod()
	{
		return shippingMethod;
	}

	@SuppressWarnings("unchecked")
	public T setShippingMethod(String shippingMethod)
	{
		this.shippingMethod = shippingMethod;
		return (T)this;
	}

	public Date getCustomerCreateAt()
	{
		return customerCreateAt;
	}

	@SuppressWarnings("unchecked")
	public T setCustomerCreateAt(Date customerCreateAt)
	{
		this.customerCreateAt = customerCreateAt;
		return (T)this;
	}

	public PaymentRequestConfig getConfig()
	{
		return config;
	}

	@SuppressWarnings("unchecked")
	public T setConfig(PaymentRequestConfig config)
	{
		this.config = config;
		return (T)this;
	}

	public CustomerInfo getCustomerInfo()
	{
		return customerInfo;
	}

	@SuppressWarnings("unchecked")
	public T setCustomerInfo(CustomerInfo customerInfo)
	{
		this.customerInfo = customerInfo;
		return (T)this;
	}

	public PaymentInfos getPaymentInfos()
	{
		return paymentInfos;
	}

	@SuppressWarnings("unchecked")
	public T addPaymentInfo(String key, String value)
	{
		paymentInfos.add(key, value);
		return (T)this;
	}

	public boolean getUsePayPass()
	{
		return usePayPass;
	}

	@SuppressWarnings("unchecked")
	public T setUsePayPass(boolean usePayPass)
	{
		this.usePayPass = usePayPass;
		return (T)this;
	}

	public String getGiftCardToken() {
		return giftCardToken;
	}

	@SuppressWarnings("unchecked")
	public T setGiftCardToken(String giftCardToken) {
		this.giftCardToken = giftCardToken;
		return (T)this;
	}

	public List<OrderLine> getOrderLines()
	{
		return orderLines;
	}

	@SuppressWarnings("unchecked")
	public T setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
		return (T)this;
	}
}
