package com.pensio.api.request;

import com.pensio.Amount;

public class ReserveSubscriptionChargeRequest<T extends ReserveSubscriptionChargeRequest<T>>
{
	private String agreementId;
	private AgreementUnscheduledType  agreementUnscheduledType;
	private Amount amount;
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

	public ReserveSubscriptionChargeRequest(String agreementId)
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

	public ReserveSubscriptionChargeRequest setAgreementUnscheduledType(AgreementUnscheduledType agreementUnscheduledType) {
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

	public ReserveSubscriptionChargeRequest setAmount(Amount amount)
	{
		this.amount = amount;
		return this;
	}
	
}
