//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.15 at 12:03:20 PM MSK 
//


package ru.smarteps.scl;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for tDataSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tDataSet">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iec.ch/61850/2006/SCL}tNaming">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="FCDA" type="{http://www.iec.ch/61850/2006/SCL}tFCDA"/>
 *         &lt;element name="FCCB" type="{http://www.iec.ch/61850/2006/SCL}tFCCB"/>
 *       &lt;/choice>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tDataSet", propOrder = {
    "fcdaOrFCCB"
})
public class TDataSet
    extends TNaming
{

    @XmlElements({
        @XmlElement(name = "FCDA", type = TFCDA.class),
        @XmlElement(name = "FCCB", type = TFCCB.class)
    })
    protected List<Object> fcdaOrFCCB;

    /**
     * Gets the value of the fcdaOrFCCB property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fcdaOrFCCB property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFCDAOrFCCB().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TFCDA }
     * {@link TFCCB }
     * 
     * 
     */
    public List<Object> getFCDAOrFCCB() {
        if (fcdaOrFCCB == null) {
            fcdaOrFCCB = new ArrayList<Object>();
        }
        return this.fcdaOrFCCB;
    }

}
