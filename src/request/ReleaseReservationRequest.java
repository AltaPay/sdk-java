package request;


public class ReleaseReservationRequest
{
	private String paymentId;

	
	public ReleaseReservationRequest(String paymentId)
	{
		this.paymentId = paymentId;
	}

	public String getPaymentId()
	{
		return paymentId;
	}
	
}
