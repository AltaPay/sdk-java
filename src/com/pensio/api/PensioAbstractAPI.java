package com.pensio.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	protected Unmarshaller u = null;

	protected HTTPHelper httpHelper;

	
	public PensioAbstractAPI(String baseURL, String username, String password) 
	{
		this.baseURL = baseURL;
		this.username = username;
		this.password = password;
		this.httpHelper = new HTTPHelper();

		try 
		{
			JAXBContext jc = JAXBContext.newInstance("com.pensio.api.generated");
			u = jc.createUnmarshaller();
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
	}

	protected APIResponse getAPIResponse(String method,	Map<String, String> postVars)
			throws PensioAPIException 
	{
		try 
		{
			InputStream inStream = this.httpHelper.doPost(this.baseURL+getAppAPIPath()+method, postVars, username, password);
			
			@SuppressWarnings("unchecked")
			JAXBElement<APIResponse> result = (JAXBElement<APIResponse>)u.unmarshal(inStream);
			
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
	protected abstract String getAppAPIPath();
	
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



}
