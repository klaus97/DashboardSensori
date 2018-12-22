package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

public class JavaFXController extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        primaryStage.setTitle("Dashboard Ambientale");
        primaryStage.setScene(new Scene(root, 300, 285));
        primaryStage.show();
    }

    public void setdashboard(ActionEvent event) {

        Parent root;
        try {
            //setto la nuova scena della home page e nascondo la precedente
            root = FXMLLoader.load(DashboardController.class.getResource("../view/dashboard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}