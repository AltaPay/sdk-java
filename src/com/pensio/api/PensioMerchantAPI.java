package com.pensio.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import request.CaptureReservationRequest;
import request.ChargeSubscriptionRequest;
import request.PaymentRequest;
import request.PaymentReservationRequest;
import request.RefundRequest;
import request.ReleaseReservationRequest;
import request.ReserveSubscriptionChargeRequest;

import com.pensio.api.generated.APIResponse;

public class PensioMerchantAPI {

	private String baseURL;
	private String username;
	private String password;
	private Unmarshaller u = null;
	private HTTPHelper httpHelper;

	public PensioMerchantAPI(String baseURL, String username, String password) 
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




	public boolean login() throws PensioAPIException 
	{
		APIResponse response = getAPIResponse("login",
				new HashMap<String, String>());
		return "Success".equals(response.getBody().getResult());
	}
	
	public PaymentRequestResponse createPaymentRequest(PaymentRequest paymentRequest) throws PensioAPIException
	{
		HashMap<String, String> params = new HashMap<String, String>();
		setPaymentRequestParameters(paymentRequest, params);
		
		APIResponse response = getAPIResponse("createPaymentRequest", params);
		return new PaymentRequestResponseImpl()
			.setUrl(response.getBody().getUrl());
		
	}
	
	public APIResponse reservation(PaymentReservationRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		setPaymentRequestParameters(request, params);
		setCreditCardRequestParameters(request, params);
		
		return getAPIResponse("reservation", params);
	}
	
	public APIResponse capture(CaptureReservationRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getPaymentId());
		addParam(params, "amount", request.getAmountString());
		addParam(params, "reconciliation_identifier", request.getReconciliationIdentifier());
		addParam(params, "invoice_number", request.getInvoiceNumber());
		addParam(params, "sales_tax", request.getSalesTax());
		
		return getAPIResponse("captureReservation", params);
	}

	public APIResponse refund(RefundRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getPaymentId());
		addParam(params, "amount", request.getAmountString());
		addParam(params, "reconciliation_identifier", request.getReconciliationIdentifier());
		
		return getAPIResponse("refundCapturedReservation", params);
	}
	
	public APIResponse chargeSubscription(ChargeSubscriptionRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getSubscriptionId());
		addParam(params, "amount", request.getAmountString());
		addParam(params, "reconciliation_identifier", request.getReconciliationIdentifier());
		
		return getAPIResponse("chargeSubscription", params);
	}
	
	public APIResponse reserveSubscriptionCharge(ReserveSubscriptionChargeRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getSubscriptionId());
		addParam(params, "amount", request.getAmountString());
		
		return getAPIResponse("reserveSubscriptionCharge", params);
	}
	
	public APIResponse release(ReleaseReservationRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getPaymentId());
		
		return getAPIResponse("releaseReservation", params);
	}
	
	private void setCreditCardRequestParameters(
			PaymentReservationRequest request, HashMap<String, String> params)
	{
		if(request.getCreditCard() == null)
		{
			return;
		}
		
		addParam(params, "credit_card_token", request.getCreditCard().getToken());
		addParam(params, "cardnum", request.getCreditCard().getCardNumber());
		addParam(params, "emonth", request.getCreditCard().getExpiryMonth());
		addParam(params, "eyear", request.getCreditCard().getExpiryYear());
		
		addParam(params, "cvc", request.getCreditCard().getCvc());
		
	}

	protected void setPaymentRequestParameters(
		  PaymentRequest paymentRequest
		, HashMap<String, String> params
	) 
	{
		addParam(params, "terminal", paymentRequest.getTerminal());
		addParam(params, "amount", paymentRequest.getAmount().getAmountString());
		addParam(params, "currency", paymentRequest.getAmount().getCurrency().name());
		addParam(params, "shop_orderid", paymentRequest.getShopOrderId());
		if(paymentRequest.getAuthType() != null)
		{
			addParam(params, "type", paymentRequest.getAuthType().name());
		}
		addParam(params, "cookie", paymentRequest.getCookie());
		addParam(params, "ccToken", paymentRequest.getCreditCardToken());
		addParam(params, "language", paymentRequest.getLanguage());
		addParam(params, "shipping_method", paymentRequest.getShippingMethod());
		addParam(params, "customer_created_date", DateHelper.formatDate("yyyy-MM-dd HH:mm:ss", paymentRequest.getCustomerCreateAt()));
		if(paymentRequest.getConfig() != null)
		{
			addParam(params, "config[callback_form]", paymentRequest.getConfig().getCallbackForm());
			addParam(params, "config[callback_fail]", paymentRequest.getConfig().getCallbackFail());
			addParam(params, "config[callback_notification]", paymentRequest.getConfig().getCallbackNotification());
			addParam(params, "config[callback_ok]", paymentRequest.getConfig().getCallbackOk());
			addParam(params, "config[callback_open]", paymentRequest.getConfig().getCallbackOpen());
			addParam(params, "config[callback_redirect]", paymentRequest.getConfig().getCallbackRedirect());
			addParam(params, "fraud_service", paymentRequest.getConfig().getFraudService());
		}
		
		if(paymentRequest.getCustomerInfo() != null)
		{
			addParam(params, "organisation_number", paymentRequest.getCustomerInfo().getOrganisationNumber());
			addParam(params, "customer_info[email]", paymentRequest.getCustomerInfo().getEmail());
			addParam(params, "customer_info[bank_name]", paymentRequest.getCustomerInfo().getBankName());
			addParam(params, "customer_info[bank_phone]", paymentRequest.getCustomerInfo().getBankPhone());
			addParam(params, "customer_info[customer_phone]", paymentRequest.getCustomerInfo().getCustomerPhone());
			addParam(params, "customer_info[username]", paymentRequest.getCustomerInfo().getUsername());
			if(paymentRequest.getCustomerInfo().getBillingAddress() != null)
			{
				addParam(params, "customer_info[billing_address]", paymentRequest.getCustomerInfo().getBillingAddress().getAddress());
				addParam(params, "customer_info[billing_city]", paymentRequest.getCustomerInfo().getBillingAddress().getCity());
				addParam(params, "customer_info[billing_country]", paymentRequest.getCustomerInfo().getBillingAddress().getCountry());
				addParam(params, "customer_info[billing_firstname]", paymentRequest.getCustomerInfo().getBillingAddress().getFirstname());
				addParam(params, "customer_info[billing_lastname]", paymentRequest.getCustomerInfo().getBillingAddress().getLastname());
				addParam(params, "customer_info[billing_postal]", paymentRequest.getCustomerInfo().getBillingAddress().getPostal());
				addParam(params, "customer_info[billing_region]", paymentRequest.getCustomerInfo().getBillingAddress().getRegion());
			}
			if(paymentRequest.getCustomerInfo().getShippingAddress() != null)
			{
				addParam(params, "customer_info[shipping_address]", paymentRequest.getCustomerInfo().getShippingAddress().getAddress());
				addParam(params, "customer_info[shipping_city]", paymentRequest.getCustomerInfo().getShippingAddress().getCity());
				addParam(params, "customer_info[shipping_country]", paymentRequest.getCustomerInfo().getShippingAddress().getCountry());
				addParam(params, "customer_info[shipping_firstname]", paymentRequest.getCustomerInfo().getShippingAddress().getFirstname());
				addParam(params, "customer_info[shipping_lastname]", paymentRequest.getCustomerInfo().getShippingAddress().getLastname());
				addParam(params, "customer_info[shipping_postal]", paymentRequest.getCustomerInfo().getShippingAddress().getPostal());
				addParam(params, "customer_info[shipping_region]", paymentRequest.getCustomerInfo().getShippingAddress().getRegion());
			}
		}
	}

	private void addParam(HashMap<String, String> params, String key, String value) 
	{
		if(value != null)
		{
			params.put(key, value);
		}
	}

	private String getString(InputStream inStream) throws IOException 
	{
	    BufferedReader br = new BufferedReader( new InputStreamReader( inStream ) );
	    StringBuffer text = new StringBuffer();
	    for ( String line; (line = br.readLine()) != null; )
	    {
	        text.append( line );
	    }
	    		
	    return text.toString();
	}

	protected APIResponse getAPIResponse(String method,
			Map<String, String> postVars) throws PensioAPIException 
	{

		try 
		{
//			System.out.println(this.baseURL+"/merchant/API/"+method);
//			System.out.println(postVars);
			InputStream inStream = this.httpHelper.doPost(this.baseURL+"/merchant/API/"+method, postVars, username, password);
//			System.out.println(getString(inStream));
//			return null;
			
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
}
