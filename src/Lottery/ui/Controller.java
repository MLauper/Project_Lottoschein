package Lottery.ui;

import Lottery.LotteryDrawing;
import Lottery.WinningType;
import Lottery.jaxb.LotteryTicketsType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    Stage mainStage;
    LotteryTicketsType lotteryTicketsRootElement;
    LotteryDrawing lotteryDrawing;
    @FXML
    private ComboBox<?> cboxTicketID;
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
        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'Project_Lottoschein.fxml'.";
        assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'Project_Lottoschein.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'Project_Lottoschein.fxml'.";
        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'Project_Lottoschein.fxml'.";
        assert winningNo1 != null : "fx:id=\"winningNo1\" was not injected: check your FXML file 'Project_Lottoschein.fxml'.";

        Bindings.bindBidirectional(propertyWinningNo1, winningNo1.textProperty());
        xmlPath.bind(txtXMLPath.textProperty());

        for (int i = 0; i < 5; i++) {
            winningNumbers.add(i, new SimpleStringProperty());
        }
        Bindings.bindBidirectional(winningNumbers.get(0), txtWinningNo1.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(1), txtWinningNo2.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(2), txtWinningNo3.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(3), txtWinningNo4.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(4), txtWinningNo5.textProperty());
        //Setting sample initial values:
        winningNumbers.get(0).setValue("1");
        winningNumbers.get(1).setValue("2");
        winningNumbers.get(2).setValue("3");
        winningNumbers.get(3).setValue("4");
        winningNumbers.get(4).setValue("5");


        winningStarNumbers.add(new SimpleStringProperty());
        winningStarNumbers.add(new SimpleStringProperty());
        Bindings.bindBidirectional(winningStarNumbers.get(0), txtWinningStarNo1.textProperty());
        Bindings.bindBidirectional(winningStarNumbers.get(1), txtWinningStarNo2.textProperty());
        //Setting sample initial values:
        winningStarNumbers.get(0).setValue("6");
        winningStarNumbers.get(1).setValue("7");

        Bindings.bindBidirectional(winningSuperStarNumber, txtWinningSuperStar.textProperty());
        //Setting sample initial value:
        winningSuperStarNumber.setValue("fooba");

        lotteryDrawing = new LotteryDrawing();

    }

    @FXML
    void button_clicked(ActionEvent event) {
        System.out.println("Value of propertyWinnignNo1 is " + propertyWinningNo1);

    }

    @FXML
    void btnLoadXMLClicked(ActionEvent event) {
    	txtOutput.clear();
        System.out.println("XSD is readable: "+new File(txtXSDPath.getText().replace("\\","\\\\")).canRead());
             
        lotteryDrawing.setValidationXSD(new File(txtXSDPath.getText().replace("\\","\\\\")));
        ArrayList<Integer> winningNumbersInt = new ArrayList<Integer>();
        for (StringProperty w : winningNumbers){
            winningNumbersInt.add(Integer.decode(w.getValue()));
        }
        lotteryDrawing.setWinningNo(winningNumbersInt);
        ArrayList<Integer> winningStarNumbersInt = new ArrayList<Integer>();
        for (StringProperty w : winningStarNumbers){
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

        for (WinningType winningType : WinningType.values()){
            log(winningType.toString() + " has " + winners.get(winningType) + " winners");
            txtOutput.appendText(winningType.getText() + " \t " + winners.get(winningType).toString() + " winners\n");
   
        }

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
    private void log(String message) {
        if (LOG) {
            System.out.println("JavaFX Controller: " + message);
        }
    }

}

