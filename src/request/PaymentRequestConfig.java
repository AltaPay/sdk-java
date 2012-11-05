package request;

public class PaymentRequestConfig
{
	private String callbackForm;
	private String callbackOk;
	private String callbackFail;
	private String callbackRedirect;
	private String callbackOpen;
	private String callbackNotification;
	private String fraudService;
	private String accountOffer;
	
	public String getCallbackForm()
	{
		return callbackForm;
	}

	public PaymentRequestConfig setCallbackForm(String callbackForm)
	{
		this.callbackForm = callbackForm;
		return this;
	}

	public String getCallbackOk()
	{
		return callbackOk;
	}

	public PaymentRequestConfig setCallbackOk(String callbackOk)
	{
		this.callbackOk = callbackOk;
		return this;
	}

	public String getCallbackFail()
	{
		return callbackFail;
	}

	public PaymentRequestConfig setCallbackFail(String callbackFail)
	{
		this.callbackFail = callbackFail;
		return this;
	}

	public String getCallbackRedirect()
	{
		return callbackRedirect;
	}

	public PaymentRequestConfig setCallbackRedirect(String callbackRedirect)
	{
		this.callbackRedirect = callbackRedirect;
		return this;
	}

	public String getCallbackOpen()
	{
		return callbackOpen;
	}

	public PaymentRequestConfig setCallbackOpen(String callbackOpen)
	{
		this.callbackOpen = callbackOpen;
		return this;
	}

	public String getCallbackNotification()
	{
		return callbackNotification;
	}

	public PaymentRequestConfig setCallbackNotification(String callbackNotification)
	{
		this.callbackNotification = callbackNotification;
		return this;
	}

	public String getFraudService()
	{
		return fraudService;
	}

	public PaymentRequestConfig setFraudService(String fraudService)
	{
		this.fraudService = fraudService;
		return this;
	}

	public String getAccountOffer()
	{
		return accountOffer;
	}

	public PaymentRequestConfig setAccountOffer(String accountOffer)
	{
		this.accountOffer = accountOffer;
		return this;
	}
}
