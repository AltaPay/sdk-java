package com.pensio.api.request;

import com.pensio.Amount;

import java.util.Date;

public class CreateInvoiceReservationRequest
    extends PaymentRequest<CreateInvoiceReservationRequest>
{
    protected String organisationNumber;
    protected String personalIdentifyNumber;
    protected Date birthDate;

    public CreateInvoiceReservationRequest(String orderId, String terminalTitle, Amount amount)
    {
        super(orderId, terminalTitle, amount);
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
