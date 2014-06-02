package com.pensio.api;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;

public class PensioProcessorAPI extends PensioAbstractAPI
{

	public PensioProcessorAPI(String baseURL, String username, String password)
	{
		super(baseURL, username, password);
	}

	protected String getAppAPIPath()
	{
		return "processor/API/";
	}

	
	public APIResponse initiatePaymentRequest(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "terminal", paymentRequest.getTerminal());
		addParam(params, "amount", paymentRequest.getAmount().getAmountString());
		addParam(params, "currency", paymentRequest.getAmount().getCurrency().name());
		addParam(params, "shop_orderid", paymentRequest.getShopOrderId());
		addParam(params, "payment_source", paymentRequest.getSource());
		addParam(params, "cardnum", paymentRequest.getCreditCard().getCardNumber());
		addParam(params, "eyear", paymentRequest.getCreditCard().getExpiryYear());
		addParam(params, "emonth", paymentRequest.getCreditCard().getExpiryMonth());
		addParam(params, "cvc", paymentRequest.getCreditCard().getCvc());
		if(paymentRequest.getAuthType() != null)
		{
			addParam(params, "type", paymentRequest.getAuthType().name());
		}
				
		return getAPIResponse("initiatePayment", params);
	}
}
