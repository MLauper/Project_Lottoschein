
package Lottery.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NumberDrawingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumberDrawingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lottery_number" type="{http://www.leafit.ch/lottery_tickets/}LotteryNumbersSelectionType" maxOccurs="5" minOccurs="5"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumberDrawingType", namespace = "http://www.leafit.ch/lottery_tickets/", propOrder = {
    "lotteryNumber"
})
public class NumberDrawingType {

    @XmlElement(name = "lottery_number", type = Integer.class)
    protected List<Integer> lotteryNumber;

    /**
     * Gets the value of the lotteryNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lotteryNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLotteryNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getLotteryNumber() {
        if (lotteryNumber == null) {
            lotteryNumber = new ArrayList<Integer>();
        }
        return this.lotteryNumber;
    }

}
