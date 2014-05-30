package com.pensio.api;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.PaymentRequest;

public class PensioProcessorAPI extends PensioMerchantAPI
{

	public PensioProcessorAPI(String baseURL, String username, String password)
	{
		super(baseURL, username, password);
	}

	protected String getAppAPIPath()
	{
		return "processor/API/";
	}

	public APIResponse createPaymentRequest(HashMap<String, String> params)
			throws PensioAPIException
	{
		return getAPIResponse("initiatePayment", params);
	}
}
