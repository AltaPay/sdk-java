//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.07 at 02:37:13 PM CEST 
//


package com.pensio.api.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentNatureService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentNatureService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="SupportsRefunds">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="true"/>
 *               &lt;enumeration value="false"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SupportsRelease">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="true"/>
 *               &lt;enumeration value="false"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SupportsMultipleCaptures">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="true"/>
 *               &lt;enumeration value="false"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SupportsMultipleRefunds">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="true"/>
 *               &lt;enumeration value="false"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/all>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentNatureService", propOrder = {

})
public class PaymentNatureService {

    @XmlElement(name = "SupportsRefunds", required = true)
    protected String supportsRefunds;
    @XmlElement(name = "SupportsRelease", required = true)
    protected String supportsRelease;
    @XmlElement(name = "SupportsMultipleCaptures", required = true)
    protected String supportsMultipleCaptures;
    @XmlElement(name = "SupportsMultipleRefunds", required = true)
    protected String supportsMultipleRefunds;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the supportsRefunds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportsRefunds() {
        return supportsRefunds;
    }

    /**
     * Sets the value of the supportsRefunds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportsRefunds(String value) {
        this.supportsRefunds = value;
    }

    /**
     * Gets the value of the supportsRelease property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportsRelease() {
        return supportsRelease;
    }

    /**
     * Sets the value of the supportsRelease property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportsRelease(String value) {
        this.supportsRelease = value;
    }

    /**
     * Gets the value of the supportsMultipleCaptures property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportsMultipleCaptures() {
        return supportsMultipleCaptures;
    }

    /**
     * Sets the value of the supportsMultipleCaptures property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportsMultipleCaptures(String value) {
        this.supportsMultipleCaptures = value;
    }

    /**
     * Gets the value of the supportsMultipleRefunds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportsMultipleRefunds() {
        return supportsMultipleRefunds;
    }

    /**
     * Sets the value of the supportsMultipleRefunds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportsMultipleRefunds(String value) {
        this.supportsMultipleRefunds = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
