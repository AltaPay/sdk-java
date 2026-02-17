package com.pensio.api.request;

import java.util.Date;

public class CustomerInfo
{
	private String organisationNumber;
	private String organisationName;
	private String organisationEntityType;
	private String organisationVatId;
	private String email;
	private String username;
	private String customerPhone;
	private String bankName;
	private String accountIdentifier;
	private Date birthDate;
	private String bankPhone;
	private String clientIp;
	private Gender gender;
	
	private CustomerInfoAddress billingAddress;
	private CustomerInfoAddress shippingAddress;
	private BrowserData browserData;
	private String deviceId;

	public String getOrganisationNumber()
	{
		return organisationNumber;
	}

	public CustomerInfo setOrganisationNumber(String organisationNumber)
	{
		this.organisationNumber = organisationNumber;
		return this;
	}

	public String getEmail()
	{
		return email;
	}

	public CustomerInfo setEmail(String email)
	{
		this.email = email;
		return this;
	}

	public String getUsername()
	{
		return username;
	}

	public CustomerInfo setUsername(String username)
	{
		this.username = username;
		return this;
	}

	public String getCustomerPhone()
	{
		return customerPhone;
	}

	public CustomerInfo setCustomerPhone(String customerPhone)
	{
		this.customerPhone = customerPhone;
		return this;
	}

	public String getBankName()
	{
		return bankName;
	}

	public CustomerInfo setBankName(String bankName)
	{
		this.bankName = bankName;
		return this;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public CustomerInfo setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
		return this;
	}

	public String getBankPhone()
	{
		return bankPhone;
	}

	public CustomerInfo setBankPhone(String bankPhone)
	{
		this.bankPhone = bankPhone;
		return this;
	}

	public String getClientIp()
	{
		return clientIp;
	}

	public CustomerInfo setClientIp(String clientIp)
	{
		this.clientIp = clientIp;
		return this;
	}

	public CustomerInfoAddress getBillingAddress()
	{
		return billingAddress;
	}

	public CustomerInfo setBillingAddress(CustomerInfoAddress billingAddress)
	{
		this.billingAddress = billingAddress;
		return this;
	}

	public CustomerInfoAddress getShippingAddress()
	{
		return shippingAddress;
	}

	public CustomerInfo setShippingAddress(CustomerInfoAddress shippingAddress)
	{
		this.shippingAddress = shippingAddress;
		return this;
	}

	public Gender getGender() {
		return gender;
	}

	public CustomerInfo setGender(Gender gender)
	{
		this.gender = gender;
		return this;
	}

	public BrowserData getBrowserData() {
		return browserData;
	}

	public CustomerInfo setBrowserData(BrowserData browserData) {
		this.browserData = browserData;
		return this;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getOrganisationEntityType() {
		return organisationEntityType;
	}

	public void setOrganisationEntityType(String organisationEntityType) {
		this.organisationEntityType = organisationEntityType;
	}

	public String getOrganisationVatId() {
		return organisationVatId;
	}

	public void setOrganisationVatId(String organisationVatId) {
		this.organisationVatId = organisationVatId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public CustomerInfo setDeviceId(String deviceId) {
		this.deviceId = deviceId;
		return this;
	}
}
