package com.pensio.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.pensio.api.generated.APIResponse;

public class PensioMerchantAPI {

	private String baseURL;
	private String username;
	private String password;
	private boolean connected = false;
	private Unmarshaller u = null;
	private HTTPHelper httpHelper;

	public PensioMerchantAPI(String baseURL, String username, String password) {
		this.baseURL = baseURL;
		this.username = username;
		this.password = password;
		this.httpHelper = new HTTPHelper();

		try {
			JAXBContext jc = JAXBContext.newInstance("com.pensio.api.generated");
			u = jc.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	protected APIResponse getAPIResponse(String method,
			Map<String, String> postVars) {

		try {
			InputStream inStream = this.httpHelper.doPost(this.baseURL+"/merchant/API/"+method, postVars);
			@SuppressWarnings("unchecked")
			JAXBElement<APIResponse> result = (JAXBElement<APIResponse>)u.unmarshal(inStream);
			return result.getValue();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean login() {
		APIResponse response = getAPIResponse("login",
				new HashMap<String, String>());
		connected = "Success".equals(response.getBody().getResult());
		return connected;
	}
}
