//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.15 at 12:03:20 PM MSK 
//


package ru.smarteps.scl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for tFunction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tFunction">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iec.ch/61850/2006/SCL}tPowerSystemResource">
 *       &lt;sequence>
 *         &lt;element name="SubFunction" type="{http://www.iec.ch/61850/2006/SCL}tSubFunction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="GeneralEquipment" type="{http://www.iec.ch/61850/2006/SCL}tGeneralEquipment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tFunction", propOrder = {
    "subFunction",
    "generalEquipment"
})
public class TFunction
    extends TPowerSystemResource
{

    @XmlElement(name = "SubFunction")
    protected List<TSubFunction> subFunction;
    @XmlElement(name = "GeneralEquipment")
    protected List<TGeneralEquipment> generalEquipment;

    /**
     * Gets the value of the subFunction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subFunction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubFunction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSubFunction }
     * 
     * 
     */
    public List<TSubFunction> getSubFunction() {
        if (subFunction == null) {
            subFunction = new ArrayList<TSubFunction>();
        }
        return this.subFunction;
    }

    /**
     * Gets the value of the generalEquipment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generalEquipment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeneralEquipment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TGeneralEquipment }
     * 
     * 
     */
    public List<TGeneralEquipment> getGeneralEquipment() {
        if (generalEquipment == null) {
            generalEquipment = new ArrayList<TGeneralEquipment>();
        }
        return this.generalEquipment;
    }

}
