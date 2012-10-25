package request;

import com.pensio.Amount;

public class ReserveSubscriptionChargeRequest
{
	private String subscriptionId;
	private Amount amount;
	
	public ReserveSubscriptionChargeRequest(String subscriptionId)
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

	public ReserveSubscriptionChargeRequest setAmount(Amount amount)
	{
		this.amount = amount;
		return this;
	}
	
}
