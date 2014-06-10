package Lottery;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marco on 30.05.2014.
 */
public class main_test {

    public static void main(String[] args) {
        LotteryDrawing lotteryDrawing = new LotteryDrawing(new File("lottery_tickets_sample.xml"), new File("C:\\Users\\Marco\\IdeaProjects\\Project_Lottoschein\\src\\Lottery\\jaxb\\lottery_tickets.xsd"));
        lotteryDrawing.setValidationXSD(new File("C:\\Users\\Marco\\IdeaProjects\\Project_Lottoschein\\src\\Lottery\\jaxb\\lottery_tickets.xsd"));

        ArrayList<StringProperty> winningNo = new ArrayList<StringProperty>();
        winningNo.add(new SimpleStringProperty("3"));
        winningNo.add(new SimpleStringProperty("4"));
        winningNo.add(new SimpleStringProperty("5"));
        winningNo.add(new SimpleStringProperty("6"));
        winningNo.add(new SimpleStringProperty("7"));

        ArrayList<Integer> winningNoInt = new ArrayList<Integer>();
        for (StringProperty winning : winningNo) {
            winningNoInt.add(Integer.decode(winning.getValue()));
        }

        ArrayList<Integer> winningStarNoInt = new ArrayList<Integer>();
        winningStarNoInt.add(2);
        winningStarNoInt.add(3);
        winningStarNoInt.add(4);

        lotteryDrawing.setWinningNo(winningNoInt);
        lotteryDrawing.setWinningStarNo(winningStarNoInt);
        lotteryDrawing.setWinningSuperStar("fooba");

        System.out.println(lotteryDrawing);

        HashMap<WinningType, Integer> winners = lotteryDrawing.evaluateWinners();

        for (WinningType winningType : WinningType.values()){
            System.out.println(winningType.toString() + " has " + winners.get(winningType) + " winners");
        }

        new LotteryDrawing();

    }

}
