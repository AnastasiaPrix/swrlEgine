//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.15 at 12:03:20 PM MSK 
//


package ru.smarteps.scl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for tLogControl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tLogControl">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iec.ch/61850/2006/SCL}tControlWithTriggerOpt">
 *       &lt;attribute name="logName" use="required" type="{http://www.iec.ch/61850/2006/SCL}tName" />
 *       &lt;attribute name="logEna" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="reasonCode" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tLogControl")
public class TLogControl
    extends TControlWithTriggerOpt
{

    @XmlAttribute(name = "logName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String logName;
    @XmlAttribute(name = "logEna")
    protected Boolean logEna;
    @XmlAttribute(name = "reasonCode")
    protected Boolean reasonCode;

    /**
     * Gets the value of the logName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogName() {
        return logName;
    }

    /**
     * Sets the value of the logName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogName(String value) {
        this.logName = value;
    }

    /**
     * Gets the value of the logEna property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isLogEna() {
        if (logEna == null) {
            return true;
        } else {
            return logEna;
        }
    }

    /**
     * Sets the value of the logEna property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLogEna(Boolean value) {
        this.logEna = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isReasonCode() {
        if (reasonCode == null) {
            return true;
        } else {
            return reasonCode;
        }
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReasonCode(Boolean value) {
        this.reasonCode = value;
    }

}
