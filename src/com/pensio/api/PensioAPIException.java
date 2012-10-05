package com.pensio.api;

import com.pensio.api.generated.Header;

public class PensioAPIException 
	extends Exception
{
	public PensioAPIException(Exception e) 
	{
		super(e);
	}

	public PensioAPIException(Header header) 
	{
		super(header.getErrorMessage()+"["+header.getErrorCode()+"] in "+header.getPath());
	}

	private static final long serialVersionUID = -3118826446758364976L;

}
