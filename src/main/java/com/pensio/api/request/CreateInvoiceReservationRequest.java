package com.pensio.api.request;

import com.pensio.Amount;

import java.util.Date;

public class CreateInvoiceReservationRequest
    extends PaymentRequest<CreateInvoiceReservationRequest>
{
	protected String accountNumber;
	protected String bankCode;
	protected FraudService fraudService;
	protected PaymentSource paymentSource;

    protected String organisationNumber;
    protected String personalIdentifyNumber;
    protected Date birthDate;

    public CreateInvoiceReservationRequest(String orderId, String terminalTitle, Amount amount)
    {
        super(orderId, terminalTitle, amount);
    }

	public String getAccountNumber()
	{
		return accountNumber;
	}

	public CreateInvoiceReservationRequest setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
		return this;
	}

	public String getBankCode()
	{
		return bankCode;
	}

	public CreateInvoiceReservationRequest setBankCode(String bankCode)
	{
		this.bankCode = bankCode;
		return this;
	}

	public FraudService getFraudService()
	{
		return fraudService;
	}

	public CreateInvoiceReservationRequest setFraudService(FraudService fraudService)
	{
		this.fraudService = fraudService;
		return this;
	}

	public PaymentSource getPaymentSource()
	{
		return paymentSource;
	}

	public CreateInvoiceReservationRequest setPaymentSource(PaymentSource paymentSource)
	{
		this.paymentSource = paymentSource;
		return this;
	}

    public String getOrganisationNumber()
    {
        return organisationNumber;
    }

    public CreateInvoiceReservationRequest setOrganisationNumber(String organisationNumber)
    {
        this.organisationNumber = organisationNumber;
        return this;
    }

    public String getPersonalIdentifyNumber()
    {
        return personalIdentifyNumber;
    }

    public CreateInvoiceReservationRequest setPersonalIdentifyNumber(String personalIdentifyNumber)
    {
        this.personalIdentifyNumber = personalIdentifyNumber;
        return this;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public CreateInvoiceReservationRequest setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
        return this;
    }
}
