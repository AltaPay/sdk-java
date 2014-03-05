package com.pensio.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.csvreader.CsvReader;
import com.mysql.jdbc.StringUtils;
import com.pensio.Amount;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.CaptureReservationRequest;
import com.pensio.api.request.ChargeSubscriptionRequest;
import com.pensio.api.request.FundingListRequest;
import com.pensio.api.request.MultiPaymentRequestChild;
import com.pensio.api.request.MultiPaymentRequestParent;
import com.pensio.api.request.OrderLine;
import com.pensio.api.request.PaymentInfo;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;
import com.pensio.api.request.RefundRequest;
import com.pensio.api.request.ReleaseReservationRequest;
import com.pensio.api.request.ReserveSubscriptionChargeRequest;

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
		try
		{
			HashMap<String, String> params = new HashMap<String, String>();
			setPaymentRequestParameters(paymentRequest, params);
			
			APIResponse response = getAPIResponse("createPaymentRequest", params);
		
			return new PaymentRequestResponseImpl()
				.setUrl(new URL(response.getBody().getUrl()));
		}
		catch (MalformedURLException e)
		{
			throw new PensioAPIException(e);
		}
		
	}
	
	public APIResponse reservation(PaymentReservationRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		setPaymentRequestParameters(request, params);
		setCreditCardRequestParameters(request, params);
		setPaymentSource(request, params);
		
		return getAPIResponse("reservation", params);
	}
	
	private void setPaymentSource(PaymentReservationRequest request,
			HashMap<String, String> params)
	{
		addParam(params, "payment_source", request.getSource());
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
	
	public APIResponse fundingList(FundingListRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "page", String.valueOf(request.getPage()));
		
		return getAPIResponse("fundingList", params);
	}
	
	public List<FundingRecord> downloadFunding(String downloadLink) throws PensioAPIException
	{
		try
		{
			InputStream inStream = this.httpHelper.doPost(downloadLink, new HashMap<String, String>(), username, password);

			CsvReader reader = new CsvReader(inStream,';', Charset.forName("UTF-8"));
			ArrayList<FundingRecord> result = new ArrayList<FundingRecord>();
			
			if(reader.readHeaders())
			{
				while(reader.readRecord())
				{
					FundingRecord record = new FundingRecord();
					String date = reader.get("Date");
					System.out.println("Date: "+date);
					
					if (date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"))
					{
						record.setFundingDate(DateHelper.parseDate("yyyy-MM-dd hh:mm:ss", date));
					}
					else
					{
						record.setFundingDate(DateHelper.parseDate("yyyy-MM-dd", date));
					}
					
					record.setRecordType(reader.get("Type"));
					record.setId(reader.get("ID"));
					record.setReconciliationIdentifier(reader.get("Reconciliation Identifier"));
					record.setPaymentId(reader.get("Payment"));
					record.setOrderId(reader.get("Order"));
					record.setTerminal(reader.get("Terminal"));
					record.setShop(reader.get("Shop"));
					record.setPaymentAmount(Amount.get(reader.get("Transaction Amount"), reader.get("Transaction Currency")));
					record.setFundingAmount(Amount.get(reader.get("Settlement Amount"), reader.get("Settlement Currency")));
					
					String exRate = reader.get("Exchange Rate");
					if (StringUtils.isNullOrEmpty(exRate))
					{
						record.setExchangeRate(1.0);
					}
					else
					{
						record.setExchangeRate(Double.parseDouble(exRate));
					}
					
					record.setFixedFeeAmount(Amount.get(reader.get("Fixed Fee"), reader.get("Settlement Currency")));
					record.setFixedFeeVatAmount(Amount.get(reader.get("Fixed Fee VAT"), reader.get("Settlement Currency")));
					record.setRateBasedFeeAmount(Amount.get(reader.get("Rate Based Fee"), reader.get("Settlement Currency")));
					record.setRateBasedFeeVatAmount(Amount.get(reader.get("Rate Based Fee VAT"), reader.get("Settlement Currency")));
					
					result.add(record);
					
				}
			}
			reader.close();
			
			
			return result;
		}
		catch (Exception e)
		{
			throw new PensioAPIException(e);
		}
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
		if(paymentRequest.getUsePayPass())
		{
			addParam(params, "use_paypass", "true");
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
			addParam(params, "account_offer", paymentRequest.getConfig().getAccountOffer());
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
		
		for(PaymentInfo paymentInfo : paymentRequest.getPaymentInfos().getAll())
		{
			addParam(params, "transaction_info["+paymentInfo.getKey()+"]", paymentInfo.getValue());
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

	public PaymentRequestResponse createMultiPaymentRequest(MultiPaymentRequestParent multiPaymentRequest) throws PensioAPIException
	{
		try
		{
			HashMap<String, String> params = new HashMap<String, String>();
			setMultiPaymentRequestParameters(multiPaymentRequest, params);
			
			APIResponse response = getAPIResponse("createMultiPaymentRequest", params);
		
			return new PaymentRequestResponseImpl()
				.setUrl(new URL(response.getBody().getUrl()));
		}
		catch (MalformedURLException e)
		{
			throw new PensioAPIException(e);
		}
	}
	
	public APIResponse parsePostBackXMLParameter(String xmlParameter) throws PensioAPIException
	{
		try
		{
			@SuppressWarnings("unchecked")
			JAXBElement<APIResponse> result = (JAXBElement<APIResponse>)u.unmarshal(new StringReader(xmlParameter));
			return result.getValue();
		}
		catch (JAXBException e)
		{
			throw new PensioAPIException(e);
		}

	}

	private void setMultiPaymentRequestParameters(
			MultiPaymentRequestParent multiPaymentRequest,
			HashMap<String, String> params) {
		
		addParam(params, "terminal", multiPaymentRequest.getTerminal());
		addParam(params, "currency", multiPaymentRequest.getCurrency().name());
		addParam(params, "shop_orderid", multiPaymentRequest.getShopOrderId());
		if(multiPaymentRequest.getAuthType() != null)
		{
			addParam(params, "type", multiPaymentRequest.getAuthType().name());
		}
		
		addParam(params, "cookie", multiPaymentRequest.getCookie());
		addParam(params, "ccToken", multiPaymentRequest.getCreditCardToken());
		addParam(params, "language", multiPaymentRequest.getLanguage());
		if(multiPaymentRequest.getConfig() != null)
		{
			addParam(params, "config[callback_form]", multiPaymentRequest.getConfig().getCallbackForm());
			addParam(params, "config[callback_fail]", multiPaymentRequest.getConfig().getCallbackFail());
			addParam(params, "config[callback_notification]", multiPaymentRequest.getConfig().getCallbackNotification());
			addParam(params, "config[callback_ok]", multiPaymentRequest.getConfig().getCallbackOk());
			addParam(params, "config[callback_open]", multiPaymentRequest.getConfig().getCallbackOpen());
			addParam(params, "config[callback_redirect]", multiPaymentRequest.getConfig().getCallbackRedirect());
			addParam(params, "fraud_service", multiPaymentRequest.getConfig().getFraudService());
		}
		
		if(multiPaymentRequest.getCustomerInfo() != null)
		{
			addParam(params, "organisation_number", multiPaymentRequest.getCustomerInfo().getOrganisationNumber());
			addParam(params, "customer_info[email]", multiPaymentRequest.getCustomerInfo().getEmail());
			addParam(params, "customer_info[bank_name]", multiPaymentRequest.getCustomerInfo().getBankName());
			addParam(params, "customer_info[bank_phone]", multiPaymentRequest.getCustomerInfo().getBankPhone());
			addParam(params, "customer_info[customer_phone]", multiPaymentRequest.getCustomerInfo().getCustomerPhone());
			addParam(params, "customer_info[username]", multiPaymentRequest.getCustomerInfo().getUsername());
			if(multiPaymentRequest.getCustomerInfo().getBillingAddress() != null)
			{
				addParam(params, "customer_info[billing_address]", multiPaymentRequest.getCustomerInfo().getBillingAddress().getAddress());
				addParam(params, "customer_info[billing_city]", multiPaymentRequest.getCustomerInfo().getBillingAddress().getCity());
				addParam(params, "customer_info[billing_country]", multiPaymentRequest.getCustomerInfo().getBillingAddress().getCountry());
				addParam(params, "customer_info[billing_firstname]", multiPaymentRequest.getCustomerInfo().getBillingAddress().getFirstname());
				addParam(params, "customer_info[billing_lastname]", multiPaymentRequest.getCustomerInfo().getBillingAddress().getLastname());
				addParam(params, "customer_info[billing_postal]", multiPaymentRequest.getCustomerInfo().getBillingAddress().getPostal());
				addParam(params, "customer_info[billing_region]", multiPaymentRequest.getCustomerInfo().getBillingAddress().getRegion());
			}
			if(multiPaymentRequest.getCustomerInfo().getShippingAddress() != null)
			{
				addParam(params, "customer_info[shipping_address]", multiPaymentRequest.getCustomerInfo().getShippingAddress().getAddress());
				addParam(params, "customer_info[shipping_city]", multiPaymentRequest.getCustomerInfo().getShippingAddress().getCity());
				addParam(params, "customer_info[shipping_country]", multiPaymentRequest.getCustomerInfo().getShippingAddress().getCountry());
				addParam(params, "customer_info[shipping_firstname]", multiPaymentRequest.getCustomerInfo().getShippingAddress().getFirstname());
				addParam(params, "customer_info[shipping_lastname]", multiPaymentRequest.getCustomerInfo().getShippingAddress().getLastname());
				addParam(params, "customer_info[shipping_postal]", multiPaymentRequest.getCustomerInfo().getShippingAddress().getPostal());
				addParam(params, "customer_info[shipping_region]", multiPaymentRequest.getCustomerInfo().getShippingAddress().getRegion());
			}
		}
		
		for(PaymentInfo paymentInfo : multiPaymentRequest.getPaymentInfos().getAll())
		{
			addParam(params, "transaction_info["+paymentInfo.getKey()+"]", paymentInfo.getValue());
		}
		
		int i = 0;
		int orderLineIdx = 0;
		for(MultiPaymentRequestChild multiPaymentRequestChild : multiPaymentRequest.getMultiPaymentRequestChildren())
		{
			
			addParam(params, "multi["+i+"][terminal]", multiPaymentRequestChild.getTerminal());
			addParam(params, "multi["+i+"][amount]", String.valueOf(multiPaymentRequestChild.getAmount()));
			addParam(params, "multi["+i+"][shop_orderid]", multiPaymentRequestChild.getShopOrderId());
			if(multiPaymentRequestChild.getAuthType() != null)
			{
				addParam(params, "multi["+i+"][type]", multiPaymentRequestChild.getAuthType().name());
			}
			
			addParam(params, "multi["+i+"][language]", multiPaymentRequestChild.getLanguage());
			addParam(params, "multi["+i+"][shipping_method]", multiPaymentRequestChild.getShippingMethod());
			addParam(params, "multi["+i+"][sale_reconciliation_identifier]", multiPaymentRequestChild.getSaleReconciliationIdentifier());
			
			for(PaymentInfo paymentInfo : multiPaymentRequestChild.getPaymentInfos().getAll())
			{
				addParam(params, "multi["+i+"][transaction_info]["+paymentInfo.getKey()+"]", paymentInfo.getValue());
			}
			orderLineIdx = 0;
			for(OrderLine orderLine : multiPaymentRequestChild.getOrderLines())
			{
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][description]", orderLine.getDescription());
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][itemId]", orderLine.getItemId());
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][quantity]", String.valueOf(orderLine.getQuantity()));
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][unitPrice]", String.valueOf(orderLine.getUnitPrice()));
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][taxPercent]", String.valueOf(orderLine.getTaxPercent()));
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][unitCode]", orderLine.getUnitCode());
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][discount]", String.valueOf(orderLine.getDiscount()));
				addParam(params, "multi["+i+"][orderLines]["+orderLineIdx+"][goodsType]", orderLine.getGoodsType());
				orderLineIdx++;
			}
			
			i++;
		}
	}
}
