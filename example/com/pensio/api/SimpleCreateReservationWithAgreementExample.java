package com.pensio.api;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;
import com.pensio.Amount;
import com.pensio.AmountConversionException;
import com.pensio.Currency;
import com.pensio.api.generated.APIResponse;
import com.pensio.api.generated.Body;
import com.pensio.api.generated.Transaction;
import com.pensio.api.generated.Transactions;
import com.pensio.api.request.CaptureReservationRequest;
import com.pensio.api.request.CreditCard;
import com.pensio.api.request.PaymentReservationRequest;
import com.pensio.api.request.AgreementConfig;
import com.pensio.api.request.AgreementType;
import com.pensio.api.request.AgreementUnscheduledType;

public class SimpleCreateReservationWithAgreementExample {

	//This is the URL to connect to your gateway instance. If you are in doubt contact support.
	//For test, use: testgateway.altapaysecure.com
	private static String pgwUrl = "https://testgateway.altapaysecure.com/";
	private static String user = "shop api";
	private static String password = "testpassword";
	
	public static void main(String[] args) throws Throwable {
		createReservation();
	}
	
	public static String createReservation() throws PensioAPIException{
		
		PensioMerchantAPI api = new PensioMerchantAPI(pgwUrl, user, password);
                
		AgreementConfig agreementConfig = new AgreementConfig();
		agreementConfig.setAgreementType(AgreementType.unscheduled);
		agreementConfig.setAgreementUnscheduledType(AgreementUnscheduledType.incremental);

		PaymentReservationRequest reservationRequest = new PaymentReservationRequest(getRandonString(), "AltaPay Test Terminal", Amount.get(50.00, Currency.EUR))
				.setAgreementConfig(agreementConfig)
				.setCreditCard(CreditCard.get("4111111111111111", "12", "2020").setCvc("123"));
				
		APIResponse response = api.reservation(reservationRequest);
		
		Body body = response.getBody();
		String result = body.getResult();
		Transactions transactions = body.getTransactions();
		List<Transaction> transactionsList = transactions.getTransaction();
		Transaction transaction = transactionsList.get(0);
		String transactionId = transaction.getTransactionId();
		System.out.println("create reservation result: "+result);
		return transactionId;
	}
	
	private static String getRandonString(){
		SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[16];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    return "EX_AGREEMENTS_UI_"+encoder.encodeToString(bytes);
	}
	
	
}
