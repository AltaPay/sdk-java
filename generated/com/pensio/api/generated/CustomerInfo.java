//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.07 at 02:37:13 PM CEST 
//


package com.pensio.api.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="UserAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IpAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrganisationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryOfOrigin" type="{}CountryOfOrigin" minOccurs="0"/>
 *         &lt;element name="Gender" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="M"/>
 *               &lt;enumeration value="F"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BillingAddress" type="{}CustomerInfoAddress" minOccurs="0"/>
 *         &lt;element name="ShippingAddress" type="{}CustomerInfoAddress" minOccurs="0"/>
 *         &lt;element name="RegisteredAddress" type="{}CustomerInfoAddress" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerInfo", propOrder = {

})
public class CustomerInfo {

    @XmlElement(name = "UserAgent")
    protected String userAgent;
    @XmlElement(name = "IpAddress")
    protected String ipAddress;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "CustomerPhone")
    protected String customerPhone;
    @XmlElement(name = "OrganisationNumber")
    protected String organisationNumber;
    @XmlElement(name = "CountryOfOrigin")
    protected CountryOfOrigin countryOfOrigin;
    @XmlElement(name = "Gender")
    protected String gender;
    @XmlElement(name = "BillingAddress")
    protected CustomerInfoAddress billingAddress;
    @XmlElement(name = "ShippingAddress")
    protected CustomerInfoAddress shippingAddress;
    @XmlElement(name = "RegisteredAddress")
    protected CustomerInfoAddress registeredAddress;

    /**
     * Gets the value of the userAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the value of the userAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the customerPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the value of the customerPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerPhone(String value) {
        this.customerPhone = value;
    }

    /**
     * Gets the value of the organisationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisationNumber() {
        return organisationNumber;
    }

    /**
     * Sets the value of the organisationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganisationNumber(String value) {
        this.organisationNumber = value;
    }

    /**
     * Gets the value of the countryOfOrigin property.
     * 
     * @return
     *     possible object is
     *     {@link CountryOfOrigin }
     *     
     */
    public CountryOfOrigin getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Sets the value of the countryOfOrigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryOfOrigin }
     *     
     */
    public void setCountryOfOrigin(CountryOfOrigin value) {
        this.countryOfOrigin = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerInfoAddress }
     *     
     */
    public CustomerInfoAddress getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerInfoAddress }
     *     
     */
    public void setBillingAddress(CustomerInfoAddress value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the shippingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerInfoAddress }
     *     
     */
    public CustomerInfoAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Sets the value of the shippingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerInfoAddress }
     *     
     */
    public void setShippingAddress(CustomerInfoAddress value) {
        this.shippingAddress = value;
    }

    /**
     * Gets the value of the registeredAddress property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerInfoAddress }
     *     
     */
    public CustomerInfoAddress getRegisteredAddress() {
        return registeredAddress;
    }

    /**
     * Sets the value of the registeredAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerInfoAddress }
     *     
     */
    public void setRegisteredAddress(CustomerInfoAddress value) {
        this.registeredAddress = value;
    }

}
