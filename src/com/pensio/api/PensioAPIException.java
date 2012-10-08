package com.pensio.api;

import com.pensio.api.generated.Header;

public class PensioAPIException 
	extends Exception
{
	private static final long serialVersionUID = -3118826446758364976L;
	
	public PensioAPIException(Exception e) 
	{
		super(e);
	}

	public PensioAPIException(Header header) 
	{
		super(header.getErrorMessage()+"["+header.getErrorCode()+"] in "+header.getPath());
	}
}
