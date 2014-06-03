package com.pensio.api;

import java.util.HashMap;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.AuthType;
import com.pensio.api.request.PaymentReservationRequest;
import com.pensio.api.request.PaymentReservationWithAddressRequest;
import com.pensio.api.request.Verify3dRequest;

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
		HashMap<String, String> params = commonParams(paymentRequest);
		if (paymentRequest.getAuthType() != null)
		{
			addParam(params, "type", paymentRequest.getAuthType().name());
		}
				
		return getAPIResponse("initiatePayment", params);
	}

	public APIResponse initiatePaymentRequest(PaymentReservationWithAddressRequest paymentRequest)
			throws PensioAPIException
	{
		HashMap<String, String> params = commonParams(paymentRequest);
		addParam(params, "cardholderName", paymentRequest.getCardholderName());
		addParam(params, "cardholderAddress", paymentRequest.getCardholderAddress());
		addParam(params, "issueNumber", paymentRequest.getIssueNumber());
		addParam(params, "startMonth", paymentRequest.getStartMonth());
		addParam(params, "startYear", paymentRequest.getStartYear());
		
		if(paymentRequest.getAuthType() != null)
		{
			addParam(params, "type", paymentRequest.getAuthType().name());
		}
				
		return getAPIResponse("initiatePayment", params);
	}
	
	public APIResponse reservationOfFixedAmount(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		HashMap<String, String> params = commonParams(paymentRequest);
		if(paymentRequest.getAuthType() != null)
		{
			addParam(params, "type", paymentRequest.getAuthType().name());
		}
				
		return getAPIResponse("reservationOfFixedAmount", params);
	}

	public APIResponse reservationOfFixedAmountAndCapture(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		HashMap<String, String> params = commonParams(paymentRequest);
		addParam(params, "type", AuthType.paymentAndCapture.toString());
		
		return getAPIResponse("reservationOfFixedAmountAndCapture", params);
	}

	public APIResponse verify3dSecure(Verify3dRequest request)
			throws PensioAPIException
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transactionId", request.getTransactionId());
		addParam(params, "paRes", request.getPaRes());

		return getAPIResponse("verify3dSecure", params);
	}
	
	public HashMap<String, String> commonParams(PaymentReservationRequest paymentRequest)
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
		
		return params;
	}
}
