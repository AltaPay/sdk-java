package request;

import com.pensio.Amount;

public class RefundReservationRequest
{
	private String paymentId;
	private Amount amount;
	private String reconciliationIdentifier;
	
	public RefundReservationRequest(String paymentId)
	{
		this.paymentId = paymentId;
	}

	public String getPaymentId()
	{
		return paymentId;
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

	public RefundReservationRequest setAmount(Amount amount)
	{
		this.amount = amount;
		return this;
	}

	public String getReconciliationIdentifier()
	{
		return reconciliationIdentifier;
	}

	public RefundReservationRequest setReconciliationIdentifier(String reconciliationIdentifier)
	{
		this.reconciliationIdentifier = reconciliationIdentifier;
		return this;
	}

	
}
