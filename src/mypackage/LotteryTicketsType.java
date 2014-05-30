
package mypackage;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LotteryTicketsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LotteryTicketsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lottery_ticket" type="{http://www.leafit.ch/lottery_tickets/}LotteryTicketType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LotteryTicketsType", namespace = "http://www.leafit.ch/lottery_tickets/", propOrder = {
    "lotteryTicket"
})
public class LotteryTicketsType {

    @XmlElement(name = "lottery_ticket", required = true)
    protected List<LotteryTicketType> lotteryTicket;

    /**
     * Gets the value of the lotteryTicket property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lotteryTicket property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLotteryTicket().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LotteryTicketType }
     * 
     * 
     */
    public List<LotteryTicketType> getLotteryTicket() {
        if (lotteryTicket == null) {
            lotteryTicket = new ArrayList<LotteryTicketType>();
        }
        return this.lotteryTicket;
    }

}
