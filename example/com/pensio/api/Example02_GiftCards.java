package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.GiftCard;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;

public class Example02_GiftCards
{
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		
		String gatewayUrl = "http://gateway.dev.pensio.com/";
		String username = "test user";
		String password = "test password";
		
		PensioProcessorAPI processorApi = new PensioProcessorAPI(gatewayUrl, username, password);
		PensioMerchantAPI merchantApi = new PensioMerchantAPI(gatewayUrl, username, password);
		
        PaymentRequest request = new PaymentRequest("giftcard_"+String.valueOf(Math.round(Math.random()*100000)),"Test GiftCard Terminal", Amount.get(10.00, Currency.EUR));
        PaymentRequestResponse response = merchantApi.createPaymentRequest(request);

		PaymentReservationRequest reservationRequest = new PaymentReservationRequest(response.getPaymentRequestId());
		reservationRequest.setGiftCard(new GiftCard().setAccountIdentifier("88880000010000").setProvider("test"));

		APIResponse initiateResponse = processorApi.initiateGiftCardPayment(reservationRequest);

		if("Success".equals(initiateResponse.getBody().getResult()))
		{
			System.out.println("Paid successfully!");
		}
		else
		{
			System.out.println("Something went wrong, check initiateResponse");
		}
	}
}
