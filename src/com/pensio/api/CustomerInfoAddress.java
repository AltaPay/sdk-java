package com.pensio.api;

public class CustomerInfoAddress
{
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String region;
	private String postal;
	private String Country;

	public String getFirstname()
	{
		return firstname;
	}

	public CustomerInfoAddress setFirstname(String firstname)
	{
		this.firstname = firstname;
		return this;
	}

	public String getLastname()
	{
		return lastname;
	}

	public CustomerInfoAddress setLastname(String lastname)
	{
		this.lastname = lastname;
		return this;
	}

	public String getAddress()
	{
		return address;
	}

	public CustomerInfoAddress setAddress(String address)
	{
		this.address = address;
		return this;
	}

	public String getCity()
	{
		return city;
	}

	public CustomerInfoAddress setCity(String city)
	{
		this.city = city;
		return this;
	}

	public String getRegion()
	{
		return region;
	}

	public CustomerInfoAddress setRegion(String region)
	{
		this.region = region;
		return this;
	}

	public String getPostal()
	{
		return postal;
	}

	public CustomerInfoAddress setPostal(String postal)
	{
		this.postal = postal;
		return this;
	}

	public String getCountry()
	{
		return Country;
	}

	public CustomerInfoAddress setCountry(String country)
	{
		Country = country;
		return this;
	}
	

}
