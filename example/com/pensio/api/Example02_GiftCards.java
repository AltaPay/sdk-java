package com.pensio.api;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.request.GiftCard;
import com.pensio.api.request.PaymentRequest;
import com.pensio.api.request.PaymentReservationRequest;

public class Example02_GiftCards
{
	public static void main(String[] args) throws Throwable
	{
		String gatewayUrl = "http://gateway.dev.pensio.com/";
		String username = "test user";
		String password = "test password";
		
		PensioProcessorAPI processorApi = new PensioProcessorAPI(gatewayUrl, username, password);
		PensioMerchantAPI merchantApi = new PensioMerchantAPI(gatewayUrl, username, password);


		/**
		 * First we create a payment request, so we can group
		 * the subsequent gift card payments together
		 *
		 * This is done once
		 */
        PaymentRequest request = new PaymentRequest(
		          "giftcard_"+String.valueOf(Math.round(Math.random()*100000))  // order ID
		        , "Test GiftCard Terminal"  // terminal
		        , Amount.get(10.00, Currency.EUR)  // amount
        );
        PaymentRequestResponse response = merchantApi.createPaymentRequest(request);

		String paymentRequestId = response.getPaymentRequestId();


		/**
		 * For each gift card we initiate a gift card payment.
		 *
		 * Note that we supply the payment request ID we received before.
		 */
		PaymentReservationRequest reservationRequest = new PaymentReservationRequest(paymentRequestId);

		/**
		 * Here we create a test gift card payment where the card has an available amount of 100 EUR
		 */
		reservationRequest.setGiftCard(new GiftCard().setAccountIdentifier("88880000010000").setProvider("test"));
		APIResponse initiateResponse = processorApi.initiateGiftCardPayment(reservationRequest);


		/**
		 * Now inspect the result
		 */
		if("Success".equals(initiateResponse.getBody().getResult()))
		{
			// Payment has been fully paid
			System.out.println("Paid successfully!");
		}
		else if ("PartialSuccess".equals(initiateResponse.getBody().getResult()))
		{
			// Part of the payment has been paid e.g. 5 EUR of the requested 10 EUR
			System.out.println("Partially paid!");

			/**
			 * If we want the remainder of the payment to be paid, we could send
			 * the user back to AltaPay.
			 *
			 * To do this with another gift card do this again.
			 */
			PaymentReservationRequest secondGiftCardPayment = new PaymentReservationRequest(paymentRequestId);
			// here with amount 5.55 EUR
			secondGiftCardPayment.setGiftCard(new GiftCard().setAccountIdentifier("888800000555").setProvider("test"));
			APIResponse secondGiftCardPaymentResponse = processorApi.initiateGiftCardPayment(secondGiftCardPayment);

			/**
			 * Now inspect the response
			 */
		}
		else
		{
			System.out.println("Something went wrong, check initiateResponse");
		}
	}
}
