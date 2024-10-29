package com.pensio.api.request;

import com.pensio.Amount;

public class ChargeSubscriptionRequest<T extends ChargeSubscriptionRequest<T>>
{
	private String agreementId;
	private AgreementUnscheduledType agreementUnscheduledType;
	private Amount amount;
	private String reconciliationIdentifier;
	private PaymentInfos paymentInfos;

    {
        paymentInfos = new PaymentInfos();
    }

    public PaymentInfos getPaymentInfos()
    {
        return paymentInfos;
    }

    @SuppressWarnings("unchecked")
    public T addPaymentInfo(String key, String value)
    {
        paymentInfos.add(key, value);
        return (T)this;
    }

	public ChargeSubscriptionRequest(String agreementId)
	{
		this.agreementId = agreementId;
	}

	/**
	 *
	 * @deprecated getSubscriptionId() is deprecated, please use {@link #getAgreementId()} instead
	 */
	@Deprecated
	public String getSubscriptionId()
	{
		return agreementId;
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
