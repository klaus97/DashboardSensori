package controller;

import dao.DatoDao;
import dao.DatoDaoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @FXML
    private TableView<Sensore> tablesearch;
    @FXML
    private  TableColumn<Sensore,Integer> col_valore;
    @FXML
    private TableColumn<Sensore,Integer> col_max;
    @FXML
    private TableColumn<Sensore,String> col_codice, col_tipo, col_datainvio;

    private ObservableList<Sensore> oblist;

    ArrayList<Sensore>listdati=new ArrayList<>();

    public DashboardController(){}

    public void LoadDataSensor() throws SQLException {

            DatoDaoInterface datoDaoInterface = new DatoDao();
            listdati=datoDaoInterface.LoadData();
            setTable(listdati);

            SensoreController.listsensor=listdati;
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_codice.setCellValueFactory(new PropertyValueFactory<>("codices"));
        col_valore.setCellValueFactory(new PropertyValueFactory<>("valore"));
        col_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        col_datainvio.setCellValueFactory(new PropertyValueFactory<>("datainvio"));
        col_max.setCellValueFactory(new PropertyValueFactory<>("massimale"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        try {
            LoadDataSensor();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setTable(ArrayList<Sensore> listdati)
    {
        for(int i =0; i<listdati.size(); i++)
        {
            oblist.add(listdati.get(i));
            tablesearch.setItems(oblist);
            col_valore.setCellFactory(col ->{
                return new TableCell<Sensore,Integer> (){
                    protected void updateItem(Integer valore,boolean empty)
                    {
                        super.updateItem(valore,empty);
                        int i=0;
                        if (valore == null || empty) {
                            setText(null);
                            setStyle("");
                            i++;
                        } else {
                            setText(String.valueOf(valore));

                            if (valore > listdati.get(i).getMassimale()) {
                                setStyle("-fx-background-color:red");
                            } else {
                                setStyle("");
                            }
                            i++;
                        }
                    }
                };
            });
        }
    }
}
