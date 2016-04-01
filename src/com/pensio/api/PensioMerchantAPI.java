package com.pensio.api;

import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.csvreader.CsvReader;
import com.pensio.Amount;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.*;

public class PensioMerchantAPI extends PensioAbstractAPI
{

	public PensioMerchantAPI(String baseURL, String username, String password) 
	{
		super(baseURL, username, password);
	}

	public boolean login() throws PensioAPIException 
	{
		APIResponse response = getAPIResponse("login",
				new HashMap<String, String>());
		return "OK".equals(response.getBody().getResult());
	}
	
	public PaymentRequestResponse createPaymentRequest(PaymentRequest paymentRequest) throws PensioAPIException
	{
		try
		{
			HashMap<String, String> params = new HashMap<String, String>();
			setPaymentRequestParameters(paymentRequest, params);
			
			APIResponse response = getAPIResponse("createPaymentRequest", params);


			return new PaymentRequestResponseImpl()
				.setUrl(new URL(response.getBody().getUrl()))
                .setPaymentRequestId(response.getBody().getPaymentRequestId());
		}
		catch (MalformedURLException e)
		{
			throw new PensioAPIException(e);
		}
		
	}

	public APIResponse createInvoiceReservation(CreateInvoiceReservationRequest request) throws PensioAPIException
	{
		HashMap<String, String> params = new HashMap<String, String>();
		setPaymentRequestParameters(request, params);
		addParam(params, "organisationNumber", request.getOrganisationNumber());
		addParam(params, "personalIdentifyNumber", request.getPersonalIdentifyNumber());
		addParam(params, "birthDate", DateHelper.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSSZ",request.getBirthDate()));

		return getAPIResponse("createInvoiceReservation", params);
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
		if(request.getSource() != null)
		{
			addParam(params, "payment_source", request.getSource().name());
		}
	}
	
	public APIResponse capture(CaptureReservationRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getPaymentId());
		addParam(params, "amount", request.getAmountString());
		addParam(params, "reconciliation_identifier", request.getReconciliationIdentifier());
		addParam(params, "invoice_number", request.getInvoiceNumber());
		addParam(params, "sales_tax", request.getSalesTax());
		OrderLine[] orderLines = request.getOrderLines();
		for(int orderLineIdx=0; orderLineIdx<orderLines.length; orderLineIdx++)
		{
			OrderLine orderLine = orderLines[orderLineIdx];
			addParam(params, "orderLines["+orderLineIdx+"][description]", orderLine.getDescription());
			addParam(params, "orderLines["+orderLineIdx+"][itemId]", orderLine.getItemId());
			addParam(params, "orderLines["+orderLineIdx+"][quantity]", String.valueOf(orderLine.getQuantity()));
			addParam(params, "orderLines["+orderLineIdx+"][unitPrice]", String.valueOf(orderLine.getUnitPrice()));
			addParam(params, "orderLines["+orderLineIdx+"]["+orderLine.getTaxType().getName()+"]", String.valueOf(orderLine.getTaxValue()));
			addParam(params, "orderLines["+orderLineIdx+"][unitCode]", orderLine.getUnitCode());
			addParam(params, "orderLines["+orderLineIdx+"][discount]", String.valueOf(orderLine.getDiscount()));
			addParam(params, "orderLines["+orderLineIdx+"][goodsType]", orderLine.getGoodsType());
		}
		
		return getAPIResponse("captureReservation", params);
	}

	public APIResponse refund(RefundRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getPaymentId());
		addParam(params, "amount", request.getAmountString());
		addParam(params, "reconciliation_identifier", request.getReconciliationIdentifier());
		OrderLine[] orderLines = request.getOrderLines();
		for(int orderLineIdx=0; orderLineIdx<orderLines.length; orderLineIdx++)
		{
			OrderLine orderLine = orderLines[orderLineIdx];
			addParam(params, "orderLines["+orderLineIdx+"][description]", orderLine.getDescription());
			addParam(params, "orderLines["+orderLineIdx+"][itemId]", orderLine.getItemId());
			addParam(params, "orderLines["+orderLineIdx+"][quantity]", String.valueOf(orderLine.getQuantity()));
			addParam(params, "orderLines["+orderLineIdx+"][unitPrice]", String.valueOf(orderLine.getUnitPrice()));
			addParam(params, "orderLines["+orderLineIdx+"]["+orderLine.getTaxType().getName()+"]", String.valueOf(orderLine.getTaxValue()));
			addParam(params, "orderLines["+orderLineIdx+"][unitCode]", orderLine.getUnitCode());
			addParam(params, "orderLines["+orderLineIdx+"][discount]", String.valueOf(orderLine.getDiscount()));
			addParam(params, "orderLines["+orderLineIdx+"][goodsType]", orderLine.getGoodsType());
		}
		
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
			InputStream inStream = this.httpHelper.doPost(downloadLink, new HashMap<String, String>(), username, password, getSdkVersion());

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
					if (exRate == null || exRate.length() == 0)
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
	
	public APIResponse queryGiftCard(PaymentReservationRequest request) throws PensioAPIException 
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "terminal", request.getTerminal());
		addParam(params, "giftcard[token]", request.getGiftCardToken());
		
		return getAPIResponse("queryGiftCard", params);
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
		  PaymentRequest<?> paymentRequest
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
			addParam(params, "customer_info[client_ip]", paymentRequest.getCustomerInfo().getClientIp());
			if(paymentRequest.getCustomerInfo().getGender() != null)
			{
				addParam(params, "customer_info[gender]", paymentRequest.getCustomerInfo().getGender().name());
			}
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

		addOrderLines("orderLines",params, paymentRequest.getOrderLines());
	}

	protected APIResponse getAPIResponse(String method,
			Map<String, String> postVars) throws PensioAPIException 
	{

		try 
		{
//			System.out.println(this.baseURL+getAppAPIPath()+method);
//			System.out.println(postVars);
			InputStream inStream = this.httpHelper.doPost(this.baseURL+getAppAPIPath()+method, postVars, username, password, getSdkVersion());
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

	public APIResponse transactions(TransactionsRequest request) throws PensioAPIException
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transaction_id", request.getPaymentId());

		return getAPIResponse("transactions", params);
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
			addOrderLines("multi["+i+"][orderLines]",params, multiPaymentRequestChild.getOrderLines());
			
			i++;
		}
	}

	private void addOrderLines(String prepend, HashMap<String, String> params, List<OrderLine> orderLines)
	{
		int orderLineIdx = 0;
		for(OrderLine orderLine : orderLines)
        {
            addParam(params, prepend+"["+orderLineIdx+"][description]", orderLine.getDescription());
            addParam(params, prepend+"["+orderLineIdx+"][itemId]", orderLine.getItemId());
            addParam(params, prepend+"["+orderLineIdx+"][quantity]", String.valueOf(orderLine.getQuantity()));
            addParam(params, prepend+"["+orderLineIdx+"][unitPrice]", String.valueOf(orderLine.getUnitPrice()));
            addParam(params, prepend+"["+orderLineIdx+"]["+orderLine.getTaxType().getName()+"]", String.valueOf(orderLine.getTaxValue()));
            addParam(params, prepend+"["+orderLineIdx+"][taxPercent]", String.valueOf(orderLine.getTaxPercent()));
            addParam(params, prepend+"["+orderLineIdx+"][unitCode]", orderLine.getUnitCode());
            addParam(params, prepend+"["+orderLineIdx+"][discount]", String.valueOf(orderLine.getDiscount()));
            addParam(params, prepend+"["+orderLineIdx+"][goodsType]", orderLine.getGoodsType());
            orderLineIdx++;
        }
	}

	protected String getAppAPIPath()
	{
		return "merchant/API/";
	}

}
