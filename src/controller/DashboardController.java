package controller;

import dao.DatoDao;
import dao.Interface.DatoDaoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<Sensore> tablesearch;
    @FXML
    private  TableColumn<Sensore,Integer> col_valore,col_max;
    @FXML
    private TableColumn<Sensore,String> col_codice, col_tipo, col_datainvio;
    @FXML
    private TableColumn<Sensore,Boolean> col_stato;

    private ObservableList<Sensore> oblist;

    ArrayList<Sensore>listdati=new ArrayList<>();
    DatoDaoInterface datoDaoInterface = new DatoDao();

    public DashboardController(){}

    public void LoadDataSensor() throws SQLException {

            listdati = datoDaoInterface.LoadData();
            setTable(listdati);

            SensoreController.listsensor = listdati;

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_codice.setCellValueFactory(new PropertyValueFactory<>("codices"));
        col_valore.setCellValueFactory(new PropertyValueFactory<>("valore"));
        col_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        col_datainvio.setCellValueFactory(new PropertyValueFactory<>("datainvio"));
        col_stato.setCellValueFactory(new PropertyValueFactory<>("stato"));
        col_max.setCellValueFactory(new PropertyValueFactory<>("link"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        try {
            LoadDataSensor();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setTable(ArrayList<Sensore> listdati) {

        for (int i = 0; i < listdati.size(); i++) {
            oblist.add(listdati.get(i));
            tablesearch.setItems(oblist);
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
                                        System.out.print("VALORE:" + valore + " " + "TEST:" + temp.getMassimale());
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
                };
            });

    }
}