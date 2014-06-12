package Lottery;

import Lottery.jaxb.LotteryTicketType;
import Lottery.jaxb.LotteryTicketsType;
import Lottery.jaxb.PlayType;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Marco on 09.06.2014.
 */
public class LotteryDrawing {

    private File loadedXML;
    private boolean hasSingleXML;
    private boolean hasMultipleXML;
    private LotteryTicketType lotteryTicketType;
    private LotteryTicketsType lotteryTicketsType;
    private File validationXSD;
    private ArrayList<Integer> winningNo;
    private ArrayList<Integer> winningStarNo;
    private String winningSuperStar;
    private boolean winnersAreCurrent;
    private HashMap<WinningType, Integer> winners;

    public LotteryDrawing(File loadedXML, File validationXSD) {

        this.hasSingleXML = false;
        this.hasMultipleXML = false;
        this.lotteryTicketType = null;
        this.lotteryTicketsType = null;
        this.winningNo = null;
        this.winnersAreCurrent = false;
        this.winners = new HashMap<WinningType, Integer>();
        for (WinningType winningType : WinningType.values()) {
            winners.put(winningType, 0);
        }

        this.validationXSD = validationXSD;
        this.loadXML(loadedXML);
    }

    public boolean loadXML(File file) {
        this.unloadXML();

        this.loadedXML = file;

        if (!this.isXMLValid()) {
            this.unloadXML();
            return true;
        }
        try {

            Source source = new StreamSource(loadedXML);

            JAXBContext jaxbContext = JAXBContext.newInstance("Lottery.jaxb");
            Unmarshaller um = jaxbContext.createUnmarshaller();

            try {
                JAXBElement<LotteryTicketsType> lotteryTicketsRoot = um.unmarshal(source, LotteryTicketsType.class);
                lotteryTicketsType = lotteryTicketsRoot.getValue();
                lotteryTicketsType.getLotteryTicket().get(0);
                this.hasMultipleXML = true;
                return false;
            } catch (Exception e) {
                // No LotteryTickets Root Element or XML is empty, Try LotteryTicket
                try {
                    JAXBElement<LotteryTicketType> lotteryTicketRoot = um.unmarshal(source, LotteryTicketType.class);
                    lotteryTicketType = lotteryTicketRoot.getValue();
                    lotteryTicketType.getPlay().get(0);
                    this.hasSingleXML = true;
                    return false;
                } catch (Exception e1) {
                    this.unloadXML();
                    return true;
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void unloadXML() {
        this.hasSingleXML = false;
        this.hasMultipleXML = false;
        lotteryTicketType = null;
        lotteryTicketsType = null;
        loadedXML = null;
        this.winnersAreCurrent = false;
        for (WinningType winningType : WinningType.values()) {
            winners.put(winningType, 0);
        }

    }

    public boolean isXMLValid() {
        try {

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(validationXSD);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(loadedXML));

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public LotteryDrawing() {
        this.hasSingleXML = false;
        this.hasMultipleXML = false;
        this.lotteryTicketType = null;
        this.lotteryTicketsType = null;
        this.loadedXML = null;
        this.validationXSD = null;
        this.winnersAreCurrent = false;
        this.winners = new HashMap<WinningType, Integer>();
        for (WinningType winningType : WinningType.values()) {
            winners.put(winningType, 0);
        }

    }

    public String getWinningSuperStar() {

        return winningSuperStar;
    }

    public void setWinningSuperStar(String winningSuperStar) {
        this.winningSuperStar = winningSuperStar;
        this.winnersAreCurrent = false;
    }

    public ArrayList<Integer> getWinningStarNo() {
        return winningStarNo;
    }

    public void setWinningStarNo(ArrayList<Integer> winningStarNo) {
        this.winnersAreCurrent = false;
        this.winningStarNo = winningStarNo;
    }

    public ArrayList<Integer> getWinningNo() {
        return winningNo;
    }

    public void setWinningNo(ArrayList<Integer> winningNo) {
        this.winnersAreCurrent = false;
        this.winningNo = winningNo;
    }

    @Override
    public String toString() {
        return "LotteryDrawing{" +
                "loadedXML=" + loadedXML +
                ", hasSingleXML=" + hasSingleXML +
                ", hasMultipleXML=" + hasMultipleXML +
                ", lotteryTicketType=" + lotteryTicketType +
                ", lotteryTicketsType=" + lotteryTicketsType +
                ", validationXSD=" + validationXSD +
                ", winningNo=" + winningNo +
                ", winningStarNo=" + winningStarNo +
                ", winningSuperStar='" + winningSuperStar + '\'' +
                '}';
    }

    public File getValidationXSD() {

        return validationXSD;
    }

    public void setValidationXSD(File validationXSD) {
        this.validationXSD = validationXSD;
    }

    public boolean hasSingleXML() {
        return hasSingleXML;
    }

    public boolean hasMultipleXML() {
        return hasMultipleXML;
    }

    public boolean isXMLValid(File validateionXSD) {
        this.setValidationXSD(validateionXSD);
        return this.isXMLValid();
    }

    public HashMap<WinningType, Integer> evaluateWinners() {
        if (this.winnersAreCurrent) {
            return winners;
        }

        if (this.hasSingleXML) {
            evaluateSingleTicket(lotteryTicketType);
        }
        if (this.hasMultipleXML) {
            for (LotteryTicketType t : lotteryTicketsType.getLotteryTicket()) {
                evaluateSingleTicket(t);
            }
        }

        winnersAreCurrent = true;
        return winners;
    }

    private void evaluateSingleTicket(LotteryTicketType singleTicket) {
        Integer playWinningNo = 0;
        Integer playWinningStarNo = 0;
        Integer playWinningSuperStarNo = 0;
        Integer playWinningSuperStarNoFront = 0;
        Integer playWinningSuperStarNoBack = 0;

        Integer lotteryTicketWinningNo = 0;
        Integer lotteryTicketWinningStarNo = 0;
        Integer lotteryTicketWinningSuperStarNo = 0;

        for (PlayType play : singleTicket.getPlay()) {
            playWinningNo = 0;
            playWinningStarNo = 0;
            playWinningSuperStarNo = 0;
            playWinningSuperStarNoFront = 0;
            playWinningSuperStarNoBack = 0;

            for (Integer n : winningNo) {
                playWinningNo += play.getLotteryNumbers().getLotteryNumber().contains(n) ? 1 : 0;
            }
            for (Integer n : winningStarNo) {
                playWinningStarNo += play.getStarNumbers().getStarNumber().contains(n) ? 1 : 0;
            }

            for (String superStarNumbers : play.getSuperStarNumbers().getSuperStar()) {
                for (int i = 1; i <= winningSuperStar.length(); i++) {
                    if (winningSuperStar.substring(0, i).equalsIgnoreCase(superStarNumbers.substring(0, i))) {
                        playWinningSuperStarNoFront += 1;
                    }

                }
                for (int i = winningSuperStar.length() - 1; i > 0; i--) {
                    if (winningSuperStar.substring(i, winningSuperStar.length()).equalsIgnoreCase(superStarNumbers.substring(i, winningSuperStar.length()))) {
                        playWinningSuperStarNoBack += 1;
                    }
                    ;
                }
            }

            playWinningSuperStarNo = playWinningSuperStarNoFront > playWinningSuperStarNoBack ? playWinningSuperStarNoFront : playWinningSuperStarNoBack;

            // Assign highest winning play to lottery ticket
            if (playWinningNo > lotteryTicketWinningNo) {
                lotteryTicketWinningNo = playWinningNo;
                lotteryTicketWinningStarNo = playWinningStarNo;
            }
            if (playWinningStarNo > lotteryTicketWinningStarNo) {
                lotteryTicketWinningNo = playWinningNo;
                lotteryTicketWinningStarNo = playWinningStarNo;
            }
            if (playWinningSuperStarNo > lotteryTicketWinningSuperStarNo) {
                lotteryTicketWinningSuperStarNo = playWinningSuperStarNo;
            }
        }

        // FIVE_WITH_TWO_STARS,
        if (lotteryTicketWinningNo == 5 && lotteryTicketWinningStarNo == 2) {
            winners.put(WinningType.FIVE_WITH_TWO_STARS, winners.get(WinningType.FIVE_WITH_TWO_STARS) + 1);
        }
        // FIVE_WITH_ONE_STAR,
        else if (lotteryTicketWinningNo == 5 && lotteryTicketWinningStarNo == 1) {
            winners.put(WinningType.FIVE_WITH_ONE_STAR, winners.get(WinningType.FIVE_WITH_ONE_STAR) + 1);
        }
        // FIVE_WITH_ZERO_STARS,
        else if (lotteryTicketWinningNo == 5 && lotteryTicketWinningStarNo == 0) {
            winners.put(WinningType.FIVE_WITH_ZERO_STARS, winners.get(WinningType.FIVE_WITH_ZERO_STARS) + 1);
        }
        // FOUR_WITH_TWO_STARS,
        else if (lotteryTicketWinningNo == 4 && lotteryTicketWinningStarNo == 2) {
            winners.put(WinningType.FOUR_WITH_TWO_STARS, winners.get(WinningType.FOUR_WITH_TWO_STARS) + 1);
        }
        // FOUR_WITH_ONE_STAR,
        else if (lotteryTicketWinningNo == 4 && lotteryTicketWinningStarNo == 1) {
            winners.put(WinningType.FOUR_WITH_ONE_STAR, winners.get(WinningType.FOUR_WITH_ONE_STAR) + 1);
        }
        // FOUR_WITH_ZERO_STARS,
        else if (lotteryTicketWinningNo == 4 && lotteryTicketWinningStarNo == 0) {
            winners.put(WinningType.FOUR_WITH_ZERO_STARS, winners.get(WinningType.FOUR_WITH_ZERO_STARS) + 1);
        }
        // THREE_WITH_TWO_STARS,
        else if (lotteryTicketWinningNo == 3 && lotteryTicketWinningStarNo == 2) {
            winners.put(WinningType.THREE_WITH_TWO_STARS, winners.get(WinningType.THREE_WITH_TWO_STARS) + 1);
        }
        // THREE_WITH_ONE_STAR,
        else if (lotteryTicketWinningNo == 3 && lotteryTicketWinningStarNo == 1) {
            winners.put(WinningType.THREE_WITH_ONE_STAR, winners.get(WinningType.THREE_WITH_ONE_STAR) + 1);
        }
        // THREE_WITH_ZERO_STARS,
        else if (lotteryTicketWinningNo == 3 && lotteryTicketWinningStarNo == 0) {
            winners.put(WinningType.THREE_WITH_ZERO_STARS, winners.get(WinningType.THREE_WITH_ZERO_STARS) + 1);
        }
        // TWO_WITH_TWO_STARS,
        else if (lotteryTicketWinningNo == 2 && lotteryTicketWinningStarNo == 2) {
            winners.put(WinningType.TWO_WITH_TWO_STARS, winners.get(WinningType.TWO_WITH_TWO_STARS) + 1);
        }
        // TWO_WITH_ONE_STAR,
        else if (lotteryTicketWinningNo == 2 && lotteryTicketWinningStarNo == 1) {
            winners.put(WinningType.TWO_WITH_ONE_STAR, winners.get(WinningType.TWO_WITH_ONE_STAR) + 1);
        }
        // TWO_WITH_ZERO_STARS,
        else if (lotteryTicketWinningNo == 2 && lotteryTicketWinningStarNo == 0) {
            winners.put(WinningType.TWO_WITH_ZERO_STARS, winners.get(WinningType.TWO_WITH_ZERO_STARS) + 1);
        }
        // ONE_WITH_TWO_STARS,
        else if (lotteryTicketWinningNo == 1 && lotteryTicketWinningStarNo == 2) {
            winners.put(WinningType.ONE_WITH_TWO_STARS, winners.get(WinningType.ONE_WITH_TWO_STARS) + 1);
        }

        // ONE_SUPER_STAR,
        if (lotteryTicketWinningSuperStarNo == 1) {
            winners.put(WinningType.ONE_SUPER_STAR, winners.get(WinningType.ONE_SUPER_STAR) + 1);
        }
        // TWO_SUPER_STAR,
        else if (lotteryTicketWinningSuperStarNo == 2) {
            winners.put(WinningType.TWO_SUPER_STAR, winners.get(WinningType.TWO_SUPER_STAR) + 1);
        }
        // THREE_SUPER_STAR,
        else if (lotteryTicketWinningSuperStarNo == 3) {
            winners.put(WinningType.THREE_SUPER_STAR, winners.get(WinningType.THREE_SUPER_STAR) + 1);
        }
        // FOUR_SUPER_STAR,
        else if (lotteryTicketWinningSuperStarNo == 4) {
            winners.put(WinningType.FOUR_SUPER_STAR, winners.get(WinningType.FOUR_SUPER_STAR) + 1);
        }
        // FIVE_SUPER_STAR
        else if (lotteryTicketWinningSuperStarNo == 5) {
            winners.put(WinningType.FIVE_SUPER_STAR, winners.get(WinningType.FIVE_SUPER_STAR) + 1);
        }

    }
    public ArrayList<Integer> getTicketIDs(){
    	ArrayList<Integer> ticketIDlist = new ArrayList<Integer>();
        if (this.hasSingleXML) {
        	ticketIDlist.add(lotteryTicketsType.getLotteryTicket().get(0).getIdentifier());
        }
        if (this.hasMultipleXML) {
            for (LotteryTicketType t : lotteryTicketsType.getLotteryTicket()) {
                ticketIDlist.add(t.getIdentifier());
            }
        }

		return ticketIDlist;

    }
    public int getTicketQuantity(){
		return this.getTicketIDs().size();
    }
    public List<Integer> getNumbers(int ticketId, int play){
        if (this.hasSingleXML) {
        	return lotteryTicketType.getPlay().get(play).getLotteryNumbers().getLotteryNumber();
        }
        if (this.hasMultipleXML) {
        	return lotteryTicketsType.getLotteryTicket().get(ticketId).getPlay().get(play).getLotteryNumbers().getLotteryNumber();
        }
        return null;
    }
    public List<Integer> getStarNumbers(int ticketId, int play){
        if (this.hasSingleXML) {
        	return lotteryTicketType.getPlay().get(play).getStarNumbers().getStarNumber();
        }
        if (this.hasMultipleXML) {
        	return lotteryTicketsType.getLotteryTicket().get(ticketId).getPlay().get(play).getStarNumbers().getStarNumber();
        }
        return null;
    }
    public List<String> getSuperStarNumbers(int ticketId, int play){
        if (this.hasSingleXML) {
        	return lotteryTicketType.getPlay().get(play).getSuperStarNumbers().getSuperStar();
        }
        if (this.hasMultipleXML) {
        	return lotteryTicketsType.getLotteryTicket().get(ticketId).getPlay().get(play).getSuperStarNumbers().getSuperStar();
        }
        return null;
    }
}
