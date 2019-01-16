package controller;

import dao.Interface.SensoreDaoInterface;
import dao.SensoreDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Sensore;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class GestioneMax implements Initializable {

    @FXML
    private Button Button_save;
    @FXML
    private TextField txt_newval;
    @FXML
    private Label label_val;

    private static Sensore max;
    Alert resp = new Alert(null);
    SensoreDaoInterface savemax = new SensoreDao();

    public GestioneMax(){}

    public void SetInfo(Sensore s){max=s;}

    public void SaveMax(){

        //Controllo se nel campo non viene inserito nulla
        if(!txt_newval.getText().matches("")){

            try {
                max.setMassimale(Integer.parseInt(txt_newval.getText()));
                Integer result = savemax.SaveMax(max);

                if(result==1){
                    resp.setAlertType(Alert.AlertType.INFORMATION);
                    resp.setContentText("Il salvataggio è avvenuto con successo!");
                }else{
                    resp.setAlertType(Alert.AlertType.ERROR);
                    resp.setContentText("Errore nel salvataggio");
                }

                resp.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            resp.setAlertType(Alert.AlertType.ERROR);
            resp.setContentText("Il campo non puo' essere vuoto, riprovare!");
            resp.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_val.setText(max.getMassimale().toString());
    }


}
