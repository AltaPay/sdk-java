package com.pensio.api.request;

public class PaymentInfo
{
	private String key;
	private String value;
	
	public String getKey()
	{
		return key;
	}
	
	public PaymentInfo setKey(String key)
	{
		this.key = key;
		return this;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public PaymentInfo setValue(String value)
	{
		this.value = value;
		return this;
	}
}
