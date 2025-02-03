package com.pensio;

public enum TaxType
{
	AMOUNT("taxAmount")
	, PERCENT("taxPercent");
	
	private final String name;

	private TaxType(String name)
	{ 
		this.name = name; 
	}

	public String getName() 
	{
		return name;
	}
};
