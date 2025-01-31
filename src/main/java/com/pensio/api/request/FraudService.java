package com.pensio.api.request;

/**
 * Created by emerson on 6/9/16.
 */
public enum FraudService
{
	None,
	Maxmind,
	Red,
	Test;

	@Override public String toString()
	{
		return super.toString().toLowerCase();
	}
}
