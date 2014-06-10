package Lottery.ui;

import Lottery.jaxb.LotteryTicketsType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.action.Action;
import org.xml.sax.SAXException;

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
import java.io.IOException;
import java.util.Vector;


public class Controller {

    private static Stage primaryStage;
    private final boolean LOG = true;
    StringProperty propertyWinningNo1 = new SimpleStringProperty("1");
    StringProperty xmlPath = new SimpleStringProperty();
    Stage mainStage;
    LotteryTicketsType lotteryTicketsRootElement;

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

        for (int i = 0; i <= 5; i++) {
            winningNumbers.add(i, new SimpleStringProperty());
        }
        Bindings.bindBidirectional(winningNumbers.get(0), txtWinningNo1.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(1), txtWinningNo2.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(2), txtWinningNo3.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(3), txtWinningNo4.textProperty());
        Bindings.bindBidirectional(winningNumbers.get(4), txtWinningNo5.textProperty());

    }





    @FXML
    void button_clicked(ActionEvent event) {
        System.out.println("Value of propertyWinnignNo1 is " + propertyWinningNo1);

    }

    @FXML
    void btnLoadXMLClicked(ActionEvent event) {
        log("btnLoadXMLClicked");
        log("Path: " + xmlPath.getValue());

        if (!validateXML()) {
            log("XML not Valid!");
            Action response = org.controlsfx.dialog.Dialogs.create()
                    .owner(null)
                    .title("Test")
                    .masthead("just ...")
                    .message("Testmessage...")
                    .showConfirm();

            return;
        }

        try {
            File file = new File(xmlPath.getValue());
            Source source = new StreamSource(file);

            JAXBContext jaxbContext = JAXBContext.newInstance("Lottery");

            Unmarshaller um = jaxbContext.createUnmarshaller();

            JAXBElement<LotteryTicketsType> root = um.unmarshal(source, LotteryTicketsType.class);
            lotteryTicketsRootElement = root.getValue();

            log(String.valueOf(lotteryTicketsRootElement.getLotteryTicket().get(0).getIdentifier()));
        } catch (
                JAXBException e
                ) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        if (LOG) {
            System.out.println("JavaFX Controller: " + message);
        }
    }

    private boolean validateXML() {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(txtXSDPath.getText()));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(txtXMLPath.getText())));
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}

