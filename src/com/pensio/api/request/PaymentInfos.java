package com.pensio.api.request;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaymentInfos
{
	private Map<String, PaymentInfo> map;

	public PaymentInfos()
	{
		map = new HashMap<String, PaymentInfo>();
	}
	
	public PaymentInfos add(String key, String value)
	{
		map.put(key, new PaymentInfo().setKey(key).setValue(value));
		return this;
	}
	
	public PaymentInfo get(String key)
	{
		return map.get(key);
	}
	
	public Collection<PaymentInfo> getAll()
	{
		return map.values();
	}
}
