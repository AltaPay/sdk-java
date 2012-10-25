package request;

import com.pensio.Amount;

public class ChargeSubscriptionRequest
{
	private String subscriptionId;
	private Amount amount;
	private String reconciliationIdentifier;
	
	public ChargeSubscriptionRequest(String subscriptionId)
	{
		this.subscriptionId = subscriptionId;
	}

	public String getSubscriptionId()
	{
		return subscriptionId;
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
