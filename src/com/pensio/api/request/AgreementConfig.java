package com.pensio.api.request;

public class AgreementConfig
{
	private String agreementId;
	private AgreementType agreementType;
	private AgreementUnscheduledType agreementUnscheduledType;
	private Date agreementExpiry;
	private String agreementFrequency;
	private Date agreementNextChargeDate;
	private String agreementAdminUrl;

	public String getAgreementId()
	{
		return agreementId;
	}

	public AgreementConfig setAgreementId(String agreementId)
	{
		this.agreementId = agreementId;
		return this;
	}

	public AgreementType getAgreementType()
	{
		return agreementType;
	}

	@SuppressWarnings("unchecked")
	public T setAgreementType(AgreementType agreementType)
	{
		this.agreementType = agreementType;
		return (T)this;
	}

	public AgreementUnscheduledType getAgreementUnscheduledType()
	{
		return agreementUnscheduledType;
	}

	@SuppressWarnings("unchecked")
	public T setAgreementUnscheduledType(AgreementUnscheduledType agreementUnscheduledType)
	{
		this.agreementUnscheduledType = agreementUnscheduledType;
		return (T)this;
	}

	public Date getAgreementExpiry()
	{
		return agreementExpiry;
	}

	public AgreementConfig setAgreementExpiry(Date agreementExpiry)
	{
		this.agreementExpiry = agreementExpiry;
		return this;
	}

	public String getAgreementFrequency()
	{
		return agreementFrequency;
	}

	public AgreementConfig setAgreementFrequency(String agreementFrequency)
	{
		this.agreementFrequency = agreementFrequency;
		return this;
	}

	public Date getAgreementNextChargeDate()
	{
		return agreementNextChargeDate;
	}

	public AgreementConfig setAgreementNextChargeDate(Date agreementNextChargeDate)
	{
		this.agreementNextChargeDate = agreementNextChargeDate;
		return this;
	}

	public String getAgreementAdminUrl()
	{
		return agreementAdminUrl;
	}

	public AgreementConfig setAgreementAdminUrl(String agreementAdminUrl)
	{
		this.agreementAdminUrl = agreementAdminUrl;
		return this;
	}
}
