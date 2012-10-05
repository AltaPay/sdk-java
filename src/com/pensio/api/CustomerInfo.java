package com.pensio.api;

public class CustomerInfo
{
	private String organisationNumber;
	private String email;
	private String username;
	private String customerPhone;
	private String bankName;
	private String bankPhone;
	
	private CustomerInfoAddress billingAddress;
	private CustomerInfoAddress shippingAddress;

	public String getOrganisationNumber()
	{
		return organisationNumber;
	}

	public CustomerInfo setOrganisationNumber(String organisationNumber)
	{
		this.organisationNumber = organisationNumber;
		return this;
	}

	public String getEmail()
	{
		return email;
	}

	public CustomerInfo setEmail(String email)
	{
		this.email = email;
		return this;
	}

	public String getUsername()
	{
		return username;
	}

	public CustomerInfo setUsername(String username)
	{
		this.username = username;
		return this;
	}

	public String getCustomerPhone()
	{
		return customerPhone;
	}

	public CustomerInfo setCustomerPhone(String customerPhone)
	{
		this.customerPhone = customerPhone;
		return this;
	}

	public String getBankName()
	{
		return bankName;
	}

	public CustomerInfo setBankName(String bankName)
	{
		this.bankName = bankName;
		return this;
	}

	public String getBankPhone()
	{
		return bankPhone;
	}

	public CustomerInfo setBankPhone(String bankPhone)
	{
		this.bankPhone = bankPhone;
		return this;
	}

	public CustomerInfoAddress getBillingAddress()
	{
		return billingAddress;
	}

	public CustomerInfo setBillingAddress(CustomerInfoAddress billingAddress)
	{
		this.billingAddress = billingAddress;
		return this;
	}

	public CustomerInfoAddress getShippingAddress()
	{
		return shippingAddress;
	}

	public CustomerInfo setShippingAddress(CustomerInfoAddress shippingAddress)
	{
		this.shippingAddress = shippingAddress;
		return this;
	}
	
}
