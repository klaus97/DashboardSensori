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
import model.Gestore;
import model.Sensore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JavaFXController extends Application {

    SensoreController sensoreController = new SensoreController();
    static Stage ref = new Stage();
    static Stage ps= new Stage();
    static Timeline timeline;
    static Timeline timeline2;
    static Integer t=0;
    static ArrayList<Gestore> datagestore=new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        primaryStage.setTitle("Dashboard Ambientale");
        primaryStage.setScene(new Scene(root, 300, 285));
        primaryStage.show();
        ps=primaryStage;

            //timeline per l'invio frequente
            timeline2 = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
                System.out.print("SONO ENTRATO");
                sensoreController.GenerateVariable();
                sensoreController.InvioDatiFreq();
                JavaFXController.t=0;
            }));
            timeline2.setCycleCount(Animation.INDEFINITE);
           // timeline2.play();

            //timeline per aggiornamento dei dati e invio ogni minuto
            timeline = new Timeline(new KeyFrame(Duration.seconds(20), event -> {
                sensoreController.GenerateVariable();
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

    public void setArea(ActionEvent event){
        Parent root;

        try {
            //setto la nuova scena della home page e nascondo la precedente
            root = FXMLLoader.load(DashboardController.class.getResource("../view/area.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Selezione Area");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ps=stage;
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setZona(ActionEvent event){
        Parent root;

        try {
            //setto la nuova scena della home page e nascondo la precedente
            root = FXMLLoader.load(DashboardController.class.getResource("../view/zona.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Selezione Zona");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ps=stage;
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLuogo(ActionEvent event,String n){
        Parent root;
        LuogoController.nomezona=n;

        try {
            //setto la nuova scena della home page e nascondo la precedente
            root = FXMLLoader.load(DashboardController.class.getResource("../view/luogo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Selezione Luogo");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();
            ps=stage;
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setGestionemax(Sensore s){

        new GestioneMax().SetInfo(s);

        Parent root;
        try
        {
            root = FXMLLoader.load(JavaFXController.class.getResource("../view/gestionemax.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gestione Massimale");
            Scene home = new Scene(root);
            stage.setScene(home);
            stage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void refresh(){

        //controllo se la finestra di login è ancora aperta, cosi evito che il metodo refresh apra la finestra dei dati anche se il gestore non ha effettuato il login
        if(ps.getScene().getWindow().isShowing()){
        }else{
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

    public void ViewStage(ActionEvent event, ArrayList<Gestore> glist){

        Iterator<Gestore> itr = glist.iterator();
        datagestore=glist;

        while(itr.hasNext()){
            Gestore g=itr.next();

            if(g.getRuolo().equals("area")){
                setArea(event);
            }
            else if(g.getRuolo().equals("zona")){
                setZona(event);
            }else{
                setLuogo(event,"");
            }
        }
    }

}