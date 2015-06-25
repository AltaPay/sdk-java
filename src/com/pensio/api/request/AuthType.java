package com.pensio.api.request;

public enum AuthType
{
	payment, paymentAndCapture, subscription, subscriptionAndCharge, subscriptionAndReserve, subscription_payment, verifyCard, credit;
	
	//This is in order to recreate php tests, not sure if this the way to do it 
	public static AuthType supportDeprecatedAuthType(String type) {
		if(type=="recurring")
		{
			return AuthType.subscription;
		}
		if(type=="recurringAndCapture")
		{
			return AuthType.subscriptionAndCharge;
		}
		if(type=="recurring_payment")
		{
			return AuthType.subscription_payment;
		}
		return null;
	}

}
