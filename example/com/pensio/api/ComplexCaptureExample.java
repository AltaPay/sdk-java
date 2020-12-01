package com.pensio.api;

import java.util.ArrayList;
import java.util.List;

import com.pensio.Amount;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Body;
import com.pensio.api.generated.Transaction;
import com.pensio.api.generated.Transactions;
import com.pensio.api.request.CaptureReservationRequest;
import com.pensio.api.request.OrderLine;

public class ComplexCaptureExample {

	//This is the URL to connect to your gateway instance. If you are in doubt contact support.
	//For test, use: testgateway.altapaysecure.com
	private static String pgwUrl = "https://testgateway.altapaysecure.com/";
	private static String user = "shop api";
	private static String password = "testpassword";
	
	public static void main(String[] args) throws Throwable {
		PensioMerchantAPI api = new PensioMerchantAPI(pgwUrl, user, password);
		
		//create reservation to be captured and get it's transactionId
		String transactionId = createReservation();
		
		CaptureReservationRequest captureRequest = new CaptureReservationRequest(transactionId);

		//set amount and currency
		captureRequest.setAmount(Amount.get(10320, Currency.DKK));
		
		//set orderlines
		List<OrderLine> orderLinesList = createOrderLines();
		OrderLine[] orderLines = orderLinesList.toArray(new OrderLine[orderLinesList.size()]);
		captureRequest.setOrderLines(orderLines);
		
		//capture request
		APIResponse response = api.capture(captureRequest);
		
		Body body = response.getBody();
		String result = body.getResult();
		Transactions transactions = body.getTransactions();
		List<Transaction> transactionsList = transactions.getTransaction();
		Transaction transaction = transactionsList.get(0);

		//result of the request
		System.out.println("capture result: "+result);
		
		//if result is error, error message would be fulfilled else it's null
//		System.out.println(body.getMerchantErrorMessage());
		
		//transaction ID
//		System.out.println(transaction.getTransactionId());
		
		//total captured amount and currency from transaction
//		System.out.println(transaction.getCapturedAmount() +" "+ transaction.getCardHolderCurrencyAlpha());
	}
	
	private static List<OrderLine> createOrderLines(){
		List<OrderLine> orderLines = new ArrayList<OrderLine>();
		for(int i = 0; i<2; i++){
			OrderLine orderLine = new OrderLine("Orderline"+(i+1), "item"+(i+1), i+1, 10000000);
			orderLines.add(orderLine);
		}
		return orderLines;
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
