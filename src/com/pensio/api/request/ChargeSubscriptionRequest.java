package com.pensio.api.request;

import com.pensio.Amount;

public class ChargeSubscriptionRequest
{
	/**
	 *
	 * @deprecated subscriptionId is deprecated, please use agreementId instead.
	 */
	private String subscriptionId;
	private String agreementId;
	private AgreementUnscheduledType agreementUnscheduledType;
	private Amount amount;
	private String reconciliationIdentifier;
	
	public ChargeSubscriptionRequest(String agreementId)
	{
		this.subscriptionId = agreementId;
		this.agreementId = agreementId;
	}

	/**
	 *
	 * @deprecated getSubscriptionId() is deprecated, please use {@link #getAgreementId()} instead
	 */
	public String getSubscriptionId()
	{
		return subscriptionId;
	}

	public String getAgreementId()
	{
		return agreementId;
	}

	public AgreementUnscheduledType getAgreementUnscheduledType() {
		return agreementUnscheduledType;
	}

	public ChargeSubscriptionRequest setAgreementUnscheduledType(AgreementUnscheduledType agreementUnscheduledType) {
		this.agreementUnscheduledType = agreementUnscheduledType;
		return this;
	}

	public Amount getAmount()
	{
		return amount;
	}
	
	public String getAmountString()
	{
		if(amount == null)
		{
			return null;
		}
		return amount.getAmountString();
	}

	public ChargeSubscriptionRequest setAmount(Amount amount)
	{
		this.amount = amount;
		return this;
	}

	public String getReconciliationIdentifier()
	{
		return reconciliationIdentifier;
	}

	public ChargeSubscriptionRequest setReconciliationIdentifier(String reconciliationIdentifier)
	{
		this.reconciliationIdentifier = reconciliationIdentifier;
		return this;
	}

	
}
