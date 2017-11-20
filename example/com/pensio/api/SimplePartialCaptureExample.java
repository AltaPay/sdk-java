package com.pensio.api;

import java.util.List;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Body;
import com.pensio.api.generated.Transaction;
import com.pensio.api.generated.Transactions;
import com.pensio.api.request.CaptureReservationRequest;

public class SimplePartialCaptureExample {
	
	private static String pgwUrl = "https://vmedev.pensio.com/";
	private static String user = "shop api";
	private static String password = "testpassword";
	
	public static void main(String[] args) throws Throwable {
		PensioMerchantAPI api = new PensioMerchantAPI(pgwUrl, user, password);
		
		//create reservation to be captured and get it's transactionId
		String transactionId = createReservation();
		
		CaptureReservationRequest captureRequest = new CaptureReservationRequest(transactionId);
		
		//Amount in 3 decimal notation. An amount of 10.32 would be 10320
		captureRequest.setAmount(Amount.get(10350, Currency.DKK));
		
		APIResponse response = api.capture(captureRequest);
		
		Body body = response.getBody();
		String result = body.getResult();
		Transactions transactions = body.getTransactions();
		List<Transaction> transactionsList = transactions.getTransaction();
		Transaction transaction = transactionsList.get(0);

		//result of the request
		System.out.println(result);
		
		//if result is error, error message would be fulfilled else it's null
		System.out.println(body.getMerchantErrorMessage());
		
		//transaction ID
		System.out.println(transaction.getTransactionId());
		
		//total captured amount and currency from transaction
		System.out.println(transaction.getCapturedAmount() +" "+ transaction.getCardHolderCurrencyAlpha());
	}	
	
	private static String createReservation() {		
		try {
			return SimpleCreateReservationExample.createReservation();
		} catch (PensioAPIException e) {
			e.printStackTrace();
			return null;
		}
		
	}
		
}
