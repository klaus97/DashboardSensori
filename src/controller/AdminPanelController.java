package controller;

import dao.Interface.SensoreDaoInterface;
import dao.SensoreDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Sensore;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {

    @FXML
    private TableView<Sensore> tablesearch;
    @FXML
    private TableColumn<Sensore,Integer> col_max;
    @FXML
    private TableColumn<Sensore,String> col_codice;
    @FXML
    private TableColumn<Sensore,Boolean> col_stato;

    private ObservableList<Sensore> oblist;
    SensoreDaoInterface sensorlist= new SensoreDao();
    ArrayList<Sensore> listsens=new ArrayList<>();


    public AdminPanelController(){}

    public void LoadSensor() throws SQLException
    {
        listsens=sensorlist.LoadSensorData();
        setTable(listsens);
    }

    public void GestioneGestori(ActionEvent e){
        new JavaFXController().setGestioneGestori(e);
    }

    public void GestioneSensori(ActionEvent e){
        new JavaFXController().setGestioneSensori(e);
    }

    public void GestioneAree(ActionEvent e){
        new JavaFXController().setGestioneAree(e);
    }

    public void Logout() throws Exception {
        Stage s=new Stage();
        new JavaFXController().ps.close();
        new JavaFXController().start(s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_codice.setCellValueFactory(new PropertyValueFactory<>("codices"));
        col_stato.setCellValueFactory(new PropertyValueFactory<>("stato"));
        col_max.setCellValueFactory(new PropertyValueFactory<>("linkmax"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        try {
            LoadSensor();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setTable(ArrayList<Sensore> lsens) {

        for (int i = 0; i < lsens.size(); i++) {
            oblist.add(lsens.get(i));
            tablesearch.setItems(oblist);
        }
        col_stato.setCellFactory(col -> {
            return new TableCell<Sensore, Boolean>() {
                protected void updateItem(Boolean stato, boolean empty) {

                    if(stato!=null) {
                        super.updateItem(stato, empty);
                        if (stato == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            if (stato == true) {
                                setStyle("-fx-background-color:red");
                            } else {
                                setStyle("-fx-background-color:green");
                            }
                        }
                    }
                }
            };
        });

    }
}
