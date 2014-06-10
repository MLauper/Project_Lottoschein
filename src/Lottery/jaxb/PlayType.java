
package Lottery.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lottery_numbers" type="{http://www.leafit.ch/lottery_tickets/}NumberDrawingType"/>
 *         &lt;element name="star_numbers" type="{http://www.leafit.ch/lottery_tickets/}StarNumbersType"/>
 *         &lt;element name="super_star_numbers" type="{http://www.leafit.ch/lottery_tickets/}SuperStarType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlayType", namespace = "http://www.leafit.ch/lottery_tickets/", propOrder = {
    "lotteryNumbers",
    "starNumbers",
    "superStarNumbers"
})
public class PlayType {

    @XmlElement(name = "lottery_numbers", required = true)
    protected NumberDrawingType lotteryNumbers;
    @XmlElement(name = "star_numbers", required = true)
    protected StarNumbersType starNumbers;
    @XmlElement(name = "super_star_numbers", required = true)
    protected SuperStarType superStarNumbers;

    /**
     * Gets the value of the lotteryNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link NumberDrawingType }
     *     
     */
    public NumberDrawingType getLotteryNumbers() {
        return lotteryNumbers;
    }

    /**
     * Sets the value of the lotteryNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberDrawingType }
     *     
     */
    public void setLotteryNumbers(NumberDrawingType value) {
        this.lotteryNumbers = value;
    }

    /**
     * Gets the value of the starNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link StarNumbersType }
     *     
     */
    public StarNumbersType getStarNumbers() {
        return starNumbers;
    }

    /**
     * Sets the value of the starNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link StarNumbersType }
     *     
     */
    public void setStarNumbers(StarNumbersType value) {
        this.starNumbers = value;
    }

    /**
     * Gets the value of the superStarNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link SuperStarType }
     *     
     */
    public SuperStarType getSuperStarNumbers() {
        return superStarNumbers;
    }

    /**
     * Sets the value of the superStarNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuperStarType }
     *     
     */
    public void setSuperStarNumbers(SuperStarType value) {
        this.superStarNumbers = value;
    }

}
