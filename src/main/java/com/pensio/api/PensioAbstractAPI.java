package com.pensio.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.pensio.api.generated.APIResponse;

public abstract class PensioAbstractAPI {
	protected String baseURL;
	protected String username;
	protected String password;
	protected JAXBContext jc;

	protected HTTPHelper httpHelper;

	public PensioAbstractAPI(String baseURL, String username, String password) 
	{
		this.baseURL = baseURL;
		this.username = username;
		this.password = password;
		this.httpHelper = new HTTPHelper();

		try 
		{
			this.jc = JAXBContext.newInstance("com.pensio.api.generated");
		}
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
	}

	protected Unmarshaller getUnmarshaller() throws JAXBException {
		return this.jc.createUnmarshaller();
	}

	protected String getSdkVersion()
	{
		return SdkVersion.current();
	}
	
	protected APIResponse getAPIResponse(String method,	Map<String, String> params)
			throws PensioAPIException 
	{
		try 
		{
			InputStream inStream = this.httpHelper.doPost(this.baseURL+getAppAPIPath()+method, params, username, password, getSdkVersion());
			
			@SuppressWarnings("unchecked")
			JAXBElement<APIResponse> result = (JAXBElement<APIResponse>)getUnmarshaller().unmarshal(inStream);
			
			APIResponse response = result.getValue();
			
			if(response.getHeader().getErrorCode() != 0)
			{
				throw new PensioAPIException(response.getHeader());
			}
			
			return response;
		}
		catch (Exception e) 
		{
			throw new PensioAPIException(e);
		}
	}
	
	protected void addParam(HashMap<String, String> params, String key, String value) 
	{
		if(value != null)
		{
			params.put(key, value);
		}
	}
	
	protected String getString(InputStream inStream) throws IOException 
	{
	    BufferedReader br = new BufferedReader( new InputStreamReader( inStream ) );
	    StringBuffer text = new StringBuffer();
	    for ( String line; (line = br.readLine()) != null; )
	    {
	        text.append( line );
	    }
	    		
	    return text.toString();
	}

	protected abstract String getAppAPIPath();
}
