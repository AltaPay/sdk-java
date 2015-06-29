package com.pensio.api.request;

public enum PaymentSource
{
	moto,
	eCommerce,
	mobi;

	public static PaymentSource fromString(String source)
	{
		for (PaymentSource ps : PaymentSource.values())
		{
			if (ps.name().equals((source)))
			{
				return ps;
			}
		}

		return null;
	}
}
