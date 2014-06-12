package Lottery.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Project_Lottoschein.fxml"), ResourceBundle.getBundle("Project_Lottoschein", new Locale("de", "CH")));
        primaryStage.setTitle("Project_Lottoschein");
        primaryStage.setScene(new Scene(root, 1200, 800));
        Controller.setPrimaryStage(primaryStage);
        primaryStage.show();

    }
}
