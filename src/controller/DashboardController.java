package controller;

import dao.DatoDao;
import dao.Interface.DatoDaoInterface;
import dao.Interface.LuogoDaoInterface;
import dao.LuogoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @FXML
    private TableView<Sensore> tabledata;
    @FXML
    private TableView<Luogo> tableed;
    @FXML
    private TableColumn<Sensore,Integer> col_valore,col_max;
    @FXML
    private TableColumn<Sensore,String> col_codice, col_tipo, col_datainvio;
    @FXML
    private TableColumn<Sensore,Boolean> col_stato;
    @FXML
    private TableColumn<Luogo,String> col_stanza;
    @FXML
    private TableColumn<Luogo,String> col_piano;



    private ObservableList<Sensore> oblist;
    private ObservableList<Luogo> oblistLuogo;

    ArrayList<Sensore>listdati=new ArrayList<>();
    public static ArrayList<Luogo>dataluogo=new ArrayList<>();
    DatoDaoInterface datoDaoInterface = new DatoDao();
    LuogoDaoInterface luogoDaoInterface = new LuogoDao();

    public static Luogo luogo;

    public DashboardController(){}

    public void LoadDataSensor() throws SQLException {

        //controllo se il luogo ha una stanza allora è sicuramente un edificio altrimenti è un luogo aperto
        if(!luogo.getStanza().equals("")){
            listdati = datoDaoInterface.LoadDataEdificio(luogo);
            setTableLuogo(dataluogo);
            dataluogo.clear();
        }else {
            listdati = datoDaoInterface.LoadDataLuogoAperto(luogo);
        }
            setTable(listdati);
    }

        public void BackLuogo(ActionEvent e){
        new JavaFXController().setLuogo(e,LuogoController.nomezona);
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_codice.setCellValueFactory(new PropertyValueFactory<>("codices"));
        col_valore.setCellValueFactory(new PropertyValueFactory<>("valore"));
        col_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        col_datainvio.setCellValueFactory(new PropertyValueFactory<>("datainvio"));
        col_stato.setCellValueFactory(new PropertyValueFactory<>("stato"));
        col_max.setCellValueFactory(new PropertyValueFactory<>("linkmax"));
        col_stanza.setCellValueFactory(new PropertyValueFactory<>("stanza"));
        col_piano.setCellValueFactory(new PropertyValueFactory<>("piano"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe
        oblistLuogo=FXCollections.observableArrayList();

        try {
            LoadDataSensor();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setTableLuogo(ArrayList<Luogo>luog){
        for (int i = 0; i < luog.size(); i++) {
            oblistLuogo.add(luog.get(i));
            tableed.setItems(oblistLuogo);
        }
    }

    private void setTable(ArrayList<Sensore> listdati) {

        for (int i = 0; i < listdati.size(); i++) {
            oblist.add(listdati.get(i));
            tabledata.setItems(oblist);
        }

            col_valore.setCellFactory(col -> {
                return new TableCell<Sensore, Integer>() {
                    protected void updateItem(Integer valore, boolean empty) {

                        if(valore!= null) {

                            Iterator<Sensore> itr = listdati.iterator();
                            
                            super.updateItem(valore, empty);
                            while (itr.hasNext()) {
                                Sensore temp = itr.next();

                                if (valore == null || empty) {
                                    setText(null);
                                    setStyle("");

                                } else {
                                    setText(String.valueOf(valore));

                                    if (valore.equals(temp.getValore())) {
                                        if (valore > temp.getMassimale()) {
                                            setStyle("-fx-background-color:orange");
                                        } else {
                                            setStyle("");
                                        }
                                    }

                                }
                            }
                        }
                    }
                };
            });

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