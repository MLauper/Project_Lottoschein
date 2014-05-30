
package mypackage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mypackage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LotteryTickets_QNAME = new QName("http://www.leafit.ch/lottery_tickets/", "lottery_tickets");
    private final static QName _LotteryTicket_QNAME = new QName("http://www.leafit.ch/lottery_tickets/", "lottery_ticket");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mypackage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LotteryTicketType }
     * 
     */
    public LotteryTicketType createLotteryTicketType() {
        return new LotteryTicketType();
    }

    /**
     * Create an instance of {@link LotteryTicketsType }
     * 
     */
    public LotteryTicketsType createLotteryTicketsType() {
        return new LotteryTicketsType();
    }

    /**
     * Create an instance of {@link SuperStarType }
     * 
     */
    public SuperStarType createSuperStarType() {
        return new SuperStarType();
    }

    /**
     * Create an instance of {@link StarNumbersType }
     * 
     */
    public StarNumbersType createStarNumbersType() {
        return new StarNumbersType();
    }

    /**
     * Create an instance of {@link NumberDrawingType }
     * 
     */
    public NumberDrawingType createNumberDrawingType() {
        return new NumberDrawingType();
    }

    /**
     * Create an instance of {@link PlayType }
     * 
     */
    public PlayType createPlayType() {
        return new PlayType();
    }

    /**
     * Create an instance of {@link StartNumbersType }
     * 
     */
    public StartNumbersType createStartNumbersType() {
        return new StartNumbersType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LotteryTicketsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.leafit.ch/lottery_tickets/", name = "lottery_tickets")
    public JAXBElement<LotteryTicketsType> createLotteryTickets(LotteryTicketsType value) {
        return new JAXBElement<LotteryTicketsType>(_LotteryTickets_QNAME, LotteryTicketsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LotteryTicketType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.leafit.ch/lottery_tickets/", name = "lottery_ticket")
    public JAXBElement<LotteryTicketType> createLotteryTicket(LotteryTicketType value) {
        return new JAXBElement<LotteryTicketType>(_LotteryTicket_QNAME, LotteryTicketType.class, null, value);
    }

}
