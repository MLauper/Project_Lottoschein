
package Lottery.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StartNumbersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StartNumbersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="star_number" type="{http://www.leafit.ch/lottery_tickets/}StarNumberSelectionType" maxOccurs="2" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StartNumbersType", namespace = "http://www.leafit.ch/lottery_tickets/", propOrder = {
    "starNumber"
})
public class StartNumbersType {

    @XmlElement(name = "star_number", type = Integer.class)
    protected List<Integer> starNumber;

    /**
     * Gets the value of the starNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the starNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStarNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getStarNumber() {
        if (starNumber == null) {
            starNumber = new ArrayList<Integer>();
        }
        return this.starNumber;
    }

}
