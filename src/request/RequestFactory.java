package request;

public class RequestFactory
{
	public CaptureReservationRequest getCaptureRequest(String paymentId)
	{
		return new CaptureReservationRequest(paymentId);
	}
	
	public ChargeSubscriptionRequest getChargeRequest(String subscriptionId)
	{
		return new ChargeSubscriptionRequest(subscriptionId);
	}
	
	public PaymentRequest getPaymentRequest()
	{
		return new PaymentRequest();
	}
	
	public ReserveSubscriptionChargeRequest getReserveSubscriptionChargeRequest(String subscriptionId)
	{
		return new ReserveSubscriptionChargeRequest(subscriptionId);
	}
	
	public RefundRequest getRefundRequest(String paymentId)
	{
		return new RefundRequest(paymentId);
	}
	
}
