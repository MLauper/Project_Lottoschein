package Lottery.ui;

import Lottery.LotteryDrawing;
import Lottery.WinningType;
import Lottery.jaxb.LotteryTicketsType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Controller {

    private static Stage primaryStage;
    private final boolean LOG = true;
    StringProperty propertyWinningNo1 = new SimpleStringProperty("1");
    StringProperty xmlPath = new SimpleStringProperty();
    LotteryTicketsType lotteryTicketsRootElement;
    LotteryDrawing lotteryDrawing;

    @FXML
    private TabPane tabPanePlays;

    @FXML
    private TextField txtTicketQuantity;

    @FXML
    private TextField txtNum1;
    @FXML
    private TextField txtNum2;
    @FXML
    private TextField txtNum3;
    @FXML
    private TextField txtNum4;
    @FXML
    private TextField txtNum5;

    @FXML
    private TextField txtSNum1;
    @FXML
    private TextField txtSNum2;


    @FXML
    private TextField txtSSNum1;
    @FXML
    private TextField txtSSNum2;
    @FXML
    private TextField txtSSNum3;
    @FXML
    private ComboBox<Integer> cboxTicketID;
    @FXML
    private TextArea txtOutput;
    @FXML
    private TextField txtWinningStarNo2;
    @FXML
    private TextField txtWinningStarNo1;
    @FXML
    private TextField txtXMLPath;
    @FXML
    private TextField winningNo1;
    @FXML
    private TextField txtWinningSuperStar;
    @FXML
    private Button button;
    @FXML
    private TextField txtXSDPath;
    @FXML
    private TextField txtWinningNo5;
    @FXML
    private TextField txtWinningNo3;
    @FXML
    private TextField txtWinningNo4;
    @FXML
    private TextField txtWinningNo1;
    @FXML
    private TextField txtWinningNo2;
    @FXML
    private Font x1;
    @FXML
    private Color x2;
    @FXML
    private Font x3;
    @FXML
    private Color x4;
    private Vector<StringProperty> winningNumbers = new Vector<StringProperty>(5);
    private Vector<StringProperty> winningStarNumbers = new Vector<StringProperty>(2);
    private StringProperty winningSuperStarNumber = new SimpleStringProperty();

    public static void setPrimaryStage(Stage primaryStage) {
        Controller.primaryStage = primaryStage;
    }

    @FXML
    void initialize() {

        for (int i = 0; i < 5; i++) {
            winningNumbers.add(i, new SimpleStringProperty());
        }
        Bindings.bindBidirectional(winningNumbers.get(0), txtWinningNo1.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(1), txtWinningNo2.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(2), txtWinningNo3.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(3), txtWinningNo4.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(4), txtWinningNo5.textProperty());

        winningStarNumbers.add(new SimpleStringProperty());
        winningStarNumbers.add(new SimpleStringProperty());
        Bindings.bindBidirectional(winningStarNumbers.get(0), txtWinningStarNo1.textProperty());
        Bindings.bindBidirectional(winningStarNumbers.get(1), txtWinningStarNo2.textProperty());

        Bindings.bindBidirectional(winningSuperStarNumber, txtWinningSuperStar.textProperty());

        //Setting sample initial values:
        winningNumbers.get(0).setValue("1");
        winningNumbers.get(1).setValue("2");
        winningNumbers.get(2).setValue("3");
        winningNumbers.get(3).setValue("4");
        winningNumbers.get(4).setValue("5");

        //Setting sample initial values:
        winningStarNumbers.get(0).setValue("6");
        winningStarNumbers.get(1).setValue("7");

        //Setting sample initial value:
        winningSuperStarNumber.setValue("fooba");

        lotteryDrawing = new LotteryDrawing();

    }

    @FXML
    void btnLoadXMLClicked(ActionEvent event) {
        txtOutput.clear();
        cboxTicketID.getItems().clear();

        lotteryDrawing.setValidationXSD(new File(txtXSDPath.getText().replace("\\", "\\\\")));
        ArrayList<Integer> winningNumbersInt = new ArrayList<Integer>();
        for (StringProperty w : winningNumbers) {
            winningNumbersInt.add(Integer.decode(w.getValue()));
        }
        lotteryDrawing.setWinningNo(winningNumbersInt);
        ArrayList<Integer> winningStarNumbersInt = new ArrayList<Integer>();
        for (StringProperty w : winningStarNumbers) {
            winningStarNumbersInt.add(Integer.decode(w.getValue()));
        }
        lotteryDrawing.setWinningStarNo(winningStarNumbersInt);
        String winningSuperStarNumberString = winningSuperStarNumber.getValue();
        lotteryDrawing.setWinningSuperStar(winningSuperStarNumberString);

        lotteryDrawing.loadXML(new File(txtXMLPath.getText()));

        System.out.println(lotteryDrawing);
        // log(lotteryDrawing.toString());
        // txtOutput.appendText(lotteryDrawing.toString()+"\n\n");


        HashMap<WinningType, Integer> winners = lotteryDrawing.evaluateWinners();

        for (WinningType winningType : WinningType.values()) {
            log(winningType.toString() + " has " + winners.get(winningType) + " winners");
            txtOutput.appendText(winningType.getText() + " \t " + winners.get(winningType).toString() + " winners\n");

        }
        System.out.println("List of TicketIDs in XML: " + lotteryDrawing.getTicketIDs());

        System.out.println(lotteryDrawing.getNumbers(0, 0));

        txtTicketQuantity.setText(lotteryDrawing.getTicketQuantity() + "");

        ObservableList<Integer> ticketselector = FXCollections.observableArrayList(lotteryDrawing.getTicketIDs());
        cboxTicketID.getItems().addAll(ticketselector);
        cboxTicketID.getSelectionModel().selectFirst();

    }

    @FXML
    void btnQuitClicked(ActionEvent event) {
        System.out.println("Terminating...");
        System.exit(0);

    }

    @FXML
    void btnOpenXSD(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XSD File");
        try {
            txtXSDPath.setText(fileChooser.showOpenDialog(primaryStage)
                    .getAbsolutePath());
        } catch (NullPointerException e) {

        }


    }

    @FXML
    void btnOpenXML(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");

        try {
            txtXMLPath.setText(fileChooser.showOpenDialog(primaryStage)
                    .getAbsolutePath());
        } catch (NullPointerException e) {

        }
    }

    @FXML
    void btnClearOutputClicked(ActionEvent event) {
        txtOutput.clear();
    }

    @FXML
    void btnRefresh(ActionEvent event) {
        int selectedTicket = cboxTicketID.getSelectionModel().getSelectedIndex();
        int selectedPlay = tabPanePlays.getSelectionModel().getSelectedIndex();
    
    	try{ txtNum1.setText(""+lotteryDrawing.getNumbers(selectedTicket, selectedPlay).get(0));}catch(Exception e){txtNum1.setText("-");}
    	try{ txtNum2.setText(""+lotteryDrawing.getNumbers(selectedTicket, selectedPlay).get(1));}catch(Exception e){txtNum2.setText("-");}
    	try{ txtNum3.setText(""+lotteryDrawing.getNumbers(selectedTicket, selectedPlay).get(2));}catch(Exception e){txtNum3.setText("-");}
    	try{ txtNum4.setText(""+lotteryDrawing.getNumbers(selectedTicket, selectedPlay).get(3));}catch(Exception e){txtNum4.setText("-");}
    	try{ txtNum5.setText(""+lotteryDrawing.getNumbers(selectedTicket, selectedPlay).get(4));}catch(Exception e){txtNum5.setText("-");}
    	
    	try{ txtSNum1.setText(""+lotteryDrawing.getStarNumbers(selectedTicket, selectedPlay).get(0));}catch(Exception e){txtSNum1.setText("-");}
    	try{ txtSNum2.setText(""+lotteryDrawing.getStarNumbers(selectedTicket, selectedPlay).get(1));}catch(Exception e){txtSNum2.setText("-");}
    	
    	try{ txtSSNum1.setText(""+lotteryDrawing.getSuperStarNumbers(selectedTicket, selectedPlay).get(0));}catch(Exception e){txtSSNum1.setText("-");}
    	try{ txtSSNum2.setText(""+lotteryDrawing.getSuperStarNumbers(selectedTicket, selectedPlay).get(1));}catch(Exception e){txtSSNum2.setText("-");}
    	try{ txtSSNum3.setText(""+lotteryDrawing.getSuperStarNumbers(selectedTicket, selectedPlay).get(2));}catch(Exception e){txtSSNum3.setText("-");}
    }

    private void log(String message) {
        if (LOG) {
            System.out.println("JavaFX Controller: " + message);
        }
    }

}

