package com.pensio.api.request;

public class GiftCard
{
	protected String accountIdentifier;
	protected String accountAuthenticator;
	protected String provider;

	public String getProvider()
	{
		return provider;
	}

	public GiftCard setProvider(String provider)
	{
		this.provider = provider;
		return this;
	}

	public String getAccountIdentifier()
	{
		return accountIdentifier;
	}

	public GiftCard setAccountIdentifier(String accountIdentifier)
	{
		this.accountIdentifier = accountIdentifier;
		return this;
	}

	public String getAccountAuthenticator()
	{
		return accountAuthenticator;
	}

	public GiftCard setAccountAuthenticator(String accountAuthenticator)
	{
		this.accountAuthenticator = accountAuthenticator;
		return this;
	}
}
