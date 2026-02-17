package com.pensio.api;

import java.util.HashMap;

import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.*;

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

	public APIResponse initiateGiftCardPayment(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		if(paymentRequest.getGiftCard() == null && paymentRequest.getGiftCardToken() == null)
		{
			throw new PensioAPIException("A gift card must be supplied");
		}

		return getAPIResponse("initiateGiftCardPayment", setBaseInitiateParams(paymentRequest));
	}

	public APIResponse initiatePaymentRequest(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		if(paymentRequest.getCreditCard() == null)
		{
			throw new PensioAPIException("A credit card must be supplied");
		}

		return getAPIResponse("initiatePayment", setBaseInitiateParams(paymentRequest));
	}

	/**
	 *
	 * @deprecated use {@link #reservation(PaymentReservationRequest)} instead
	 */
    @Deprecated
	public APIResponse reservationOfFixedAmount(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		if(paymentRequest.getCreditCard() == null)
		{
			throw new PensioAPIException("A credit card must be supplied");
		}

		return getAPIResponse("reservationOfFixedAmount", setBaseInitiateParams(paymentRequest));
	}

	public APIResponse reservation(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		if(paymentRequest.getCreditCard() == null)
		{
			throw new PensioAPIException("A credit card must be supplied");
		}

		return getAPIResponse("reservation", setBaseInitiateParams(paymentRequest));
	}

	public APIResponse reservationOfFixedAmountAndCapture(PaymentReservationRequest paymentRequest)
			throws PensioAPIException
	{
		if(paymentRequest.getCreditCard() == null)
		{
			throw new PensioAPIException("A credit card must be supplied");
		}
		HashMap<String, String> params = new HashMap<>();
		commonParams(params, paymentRequest);

		addParam(params, "type", AuthType.paymentAndCapture.toString());
		
		return getAPIResponse("reservationOfFixedAmountAndCapture", params);
	}

	public APIResponse verify3dSecure(Verify3dRequest request)
			throws PensioAPIException
	{
		HashMap<String, String> params = new HashMap<String, String>();
		addParam(params, "transactionId", request.getTransactionId());
		addParam(params, "3DSecureRegular[paRes]", request.getPaRes());
		addParam(params, "3DSecureRegular[MD]", request.getTransactionId());
		addParam(params, "3DSecureV2[3ds_data]", request.getThreeDSecureData());

		return getAPIResponse("verify3dSecure", params);
	}
	public void addCustomerInfoParams(PaymentReservationRequest paymentRequest, HashMap<String, String> params)
	{
		CustomerInfo customerInfo = paymentRequest.getCustomerInfo();
		addParam(params, "customer_info[email]", customerInfo.getEmail());
		addParam(params, "customer_info[customer_phone]", customerInfo.getCustomerPhone());
		addParam(params, "customer_info[username]", customerInfo.getUsername());
		addParam(params, "customer_info[bank_name]", customerInfo.getBankName());
		addParam(params, "customer_info[bank_phone]", customerInfo.getBankPhone());
		addParam(params, "customer_info[client_ip]", customerInfo.getClientIp());
		
		CustomerInfoAddress shippingAddress = customerInfo.getShippingAddress();
		addParam(params, "customer_info[shipping_firstname]", shippingAddress.getFirstname());
		addParam(params, "customer_info[shipping_lastname]", shippingAddress.getLastname());
		addParam(params, "customer_info[shipping_address]", shippingAddress.getAddress());
		addParam(params, "customer_info[shipping_city]", shippingAddress.getCity());
		addParam(params, "customer_info[shipping_region]", shippingAddress.getRegion());
		addParam(params, "customer_info[shipping_postal]", shippingAddress.getPostal());
		addParam(params, "customer_info[shipping_country]", shippingAddress.getCountry());

		CustomerInfoAddress billingAddress = customerInfo.getBillingAddress();
		addParam(params, "customer_info[billing_firstname]", billingAddress.getFirstname());
		addParam(params, "customer_info[billing_lastname]", billingAddress.getLastname());
		addParam(params, "customer_info[billing_address]", billingAddress.getAddress());
		addParam(params, "customer_info[billing_city]", billingAddress.getCity());
		addParam(params, "customer_info[billing_region]", billingAddress.getRegion());
		addParam(params, "customer_info[billing_postal]", billingAddress.getPostal());
		addParam(params, "customer_info[billing_country]", billingAddress.getCountry());

		BrowserData browserData = customerInfo.getBrowserData();
		addParam(params, "customer_info[device_id]", browserData.getDeviceId());
		addParam(params, "customer_info[client_time_zone]", browserData.getTimeZone());
		addParam(params, "customer_info[client_javascript_enabled]", browserData.getJavascriptEnabled());
		addParam(params, "customer_info[client_screen_width]", browserData.getScreenWidth());
		addParam(params, "customer_info[client_screen_height]", browserData.getScreenHeight());
		addParam(params, "customer_info[client_color_depth]", browserData.getColorDepth());
		addParam(params, "customer_info[client_java_enabled]", browserData.getJavaEnabled());
		addParam(params, "customer_info[client_forwarded_ip]", browserData.getForwardedIp());
		addParam(params, "customer_info[client_user_agent]", browserData.getUserAgent());
		addParam(params, "customer_info[client_accept]", browserData.getAccept());
		addParam(params, "customer_info[client_accept_language]", browserData.getAcceptLanguage());
	}


	private HashMap<String, String> setBaseInitiateParams(PaymentReservationRequest paymentRequest)
	{
		HashMap<String, String> params = new HashMap<>();
		commonParams(params, paymentRequest);

		if (paymentRequest.getAuthType() != null)
		{
			addParam(params, "type", paymentRequest.getAuthType().name());
		}
		if (paymentRequest.getCustomerInfo() != null)
		{
			addCustomerInfoParams(paymentRequest, params);
		}
		return params;
	}


	public void commonParams(HashMap<String, String> params, PaymentReservationRequest paymentRequest)
	{
		addParam(params, "terminal", paymentRequest.getTerminal());
		if(paymentRequest.getAmount() != null)
		{
			addParam(params, "amount", paymentRequest.getAmount().getAmountString());
			addParam(params, "currency", paymentRequest.getAmount().getCurrency().name());
		}

		addParam(params, "shop_orderid", paymentRequest.getShopOrderId());
		if(paymentRequest.getSource() != null)
		{
			addParam(params, "payment_source", paymentRequest.getSource().name());
		}

		if(paymentRequest.getCreditCard() != null)
		{
			addParam(params, "cardnum", paymentRequest.getCreditCard().getCardNumber());
			addParam(params, "eyear", paymentRequest.getCreditCard().getExpiryYear());
			addParam(params, "emonth", paymentRequest.getCreditCard().getExpiryMonth());
			addParam(params, "cvc", paymentRequest.getCreditCard().getCvc());

			addParam(params, "cardholderName", paymentRequest.getCardholderName());
			addParam(params, "cardholderAddress", paymentRequest.getCardholderAddress());
			addParam(params, "issueNumber", paymentRequest.getIssueNumber());
			addParam(params, "startMonth", paymentRequest.getStartMonth());
			addParam(params, "startYear", paymentRequest.getStartYear());
		}
		
		if(paymentRequest.getAgreementConfig() != null)
		{
			addParam(params, "agreement[id]", paymentRequest.getAgreementConfig().getAgreementId());
			addParam(params, "agreement[type]", paymentRequest.getAgreementConfig().getAgreementType() != null ? paymentRequest.getAgreementConfig().getAgreementType().name() : null);
			addParam(params, "agreement[unscheduled_type]", paymentRequest.getAgreementConfig().getAgreementUnscheduledType() != null ? paymentRequest.getAgreementConfig().getAgreementUnscheduledType().name() : null);
			addParam(params, "agreement[expiry]", paymentRequest.getAgreementConfig().getAgreementExpiry() != null ? DateHelper.formatDate("yyyyMMdd", paymentRequest.getAgreementConfig().getAgreementExpiry()) : null);
			addParam(params, "agreement[frequency]", paymentRequest.getAgreementConfig().getAgreementFrequency());
			addParam(params, "agreement[next_charge_date]", paymentRequest.getAgreementConfig().getAgreementNextChargeDate() != null ? DateHelper.formatDate("yyyyMMdd", paymentRequest.getAgreementConfig().getAgreementNextChargeDate()) : null);
			addParam(params, "agreement[admin_url]", paymentRequest.getAgreementConfig().getAgreementAdminUrl());
			addParam(params, "agreement[retention_period]", paymentRequest.getAgreementConfig().getRetentionPeriod());
		}

		if(paymentRequest.getGiftCard() != null)
		{
			addParam(params, "giftcard[account_identifier]", paymentRequest.getGiftCard().getAccountIdentifier());
			addParam(params, "giftcard[account_authenticator]", paymentRequest.getGiftCard().getAccountAuthenticator());
			addParam(params, "giftcard[provider]", paymentRequest.getGiftCard().getProvider());
		}
		else
		{
			addParam(params, "giftcard[token]", paymentRequest.getGiftCardToken());
		}

		addParam(params, "payment_request_id", paymentRequest.getPaymentRequestId());
	}
}
