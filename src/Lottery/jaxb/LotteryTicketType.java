
package Lottery.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LotteryTicketType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LotteryTicketType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="play" type="{http://www.leafit.ch/lottery_tickets/}PlayType" maxOccurs="7"/>
 *         &lt;element name="nbr_of_valid_drawings" type="{http://www.leafit.ch/lottery_tickets/}DrawingsSelectionType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="drawing_date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LotteryTicketType", namespace = "http://www.leafit.ch/lottery_tickets/", propOrder = {
    "identifier",
    "play",
    "nbrOfValidDrawings"
})
public class LotteryTicketType {

    protected int identifier;
    @XmlElement(required = true)
    protected List<PlayType> play;
    @XmlElement(name = "nbr_of_valid_drawings")
    protected int nbrOfValidDrawings;
    @XmlAttribute(name = "drawing_date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar drawingDate;

    /**
     * Gets the value of the identifier property.
     * 
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     */
    public void setIdentifier(int value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the play property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the play property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlay().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlayType }
     * 
     * 
     */
    public List<PlayType> getPlay() {
        if (play == null) {
            play = new ArrayList<PlayType>();
        }
        return this.play;
    }

    /**
     * Gets the value of the nbrOfValidDrawings property.
     * 
     */
    public int getNbrOfValidDrawings() {
        return nbrOfValidDrawings;
    }

    /**
     * Sets the value of the nbrOfValidDrawings property.
     * 
     */
    public void setNbrOfValidDrawings(int value) {
        this.nbrOfValidDrawings = value;
    }

    /**
     * Gets the value of the drawingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDrawingDate() {
        return drawingDate;
    }

    /**
     * Sets the value of the drawingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDrawingDate(XMLGregorianCalendar value) {
        this.drawingDate = value;
    }

}
