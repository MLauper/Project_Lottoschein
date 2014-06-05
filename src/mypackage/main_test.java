package mypackage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Created by Marco on 30.05.2014.
 */
public class main_test {

    public static void main(String[] args) {


        try {

            File file = new File("lottery_tickets_sample.xml");
            Source source = new StreamSource(file);


            JAXBContext jaxbContext = JAXBContext.newInstance("mypackage");

            Unmarshaller um = jaxbContext.createUnmarshaller();

            JAXBElement<LotteryTicketsType> root = um.unmarshal(source, LotteryTicketsType.class);
            LotteryTicketsType lotteryTicketsType = root.getValue();

            //LotteryTicketType lotteryTicketsType =  (LotteryTicketType) um.unmarshal(file);

            System.out.println(lotteryTicketsType.getLotteryTicket().get(0).getIdentifier());

        } catch (
                JAXBException e
                )

        {
            e.printStackTrace();
        }
    }


}
