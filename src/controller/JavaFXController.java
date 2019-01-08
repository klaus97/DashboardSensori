package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Sensore;
import java.io.IOException;
import java.util.ArrayList;

public class JavaFXController extends Application {

    ArrayList<Sensore> listdata = new ArrayList<>();
    SensoreController sensoreController = new SensoreController();
    static Stage ref = new Stage();
    int i=0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        primaryStage.setTitle("Dashboard Ambientale");
        primaryStage.setScene(new Scene(root, 300, 285));
        primaryStage.show();


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30),event -> {
            listdata=sensoreController.GenerateVariable();
            sensoreController.InvioDati();
            refresh();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
            ref=stage;
            ((Node) (event.getSource())).getScene().getWindow().hide();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh(){


        Parent root;

        try {
            //setto la nuova scena della home page e nascondo la precedente
            root = FXMLLoader.load(DashboardController.class.getResource("../view/dashboard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ref.close();
            ref=stage;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}