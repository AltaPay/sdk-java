package request;

public class MultiPaymentRequestConfig
{
	private String callbackForm;
	private String callbackOk;
	private String callbackFail;
	private String callbackRedirect;
	private String callbackOpen;
	private String callbackNotification;
	private String fraudService;
	
	public String getCallbackForm()
	{
		return callbackForm;
	}

	public MultiPaymentRequestConfig setCallbackForm(String callbackForm)
	{
		this.callbackForm = callbackForm;
		return this;
	}

	public String getCallbackOk()
	{
		return callbackOk;
	}

	public MultiPaymentRequestConfig setCallbackOk(String callbackOk)
	{
		this.callbackOk = callbackOk;
		return this;
	}

	public String getCallbackFail()
	{
		return callbackFail;
	}

	public MultiPaymentRequestConfig setCallbackFail(String callbackFail)
	{
		this.callbackFail = callbackFail;
		return this;
	}

	public String getCallbackRedirect()
	{
		return callbackRedirect;
	}

	public MultiPaymentRequestConfig setCallbackRedirect(String callbackRedirect)
	{
		this.callbackRedirect = callbackRedirect;
		return this;
	}

	public String getCallbackOpen()
	{
		return callbackOpen;
	}

	public MultiPaymentRequestConfig setCallbackOpen(String callbackOpen)
	{
		this.callbackOpen = callbackOpen;
		return this;
	}

	public String getCallbackNotification()
	{
		return callbackNotification;
	}

	public MultiPaymentRequestConfig setCallbackNotification(String callbackNotification)
	{
		this.callbackNotification = callbackNotification;
		return this;
	}

	public String getFraudService()
	{
		return fraudService;
	}

	public MultiPaymentRequestConfig setFraudService(String fraudService)
	{
		this.fraudService = fraudService;
		return this;
	}
}
