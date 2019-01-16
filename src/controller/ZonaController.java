package controller;

import dao.AreaDao;
import dao.Interface.AreaDaoInterface;
import dao.Interface.ZonaDaoInterface;
import dao.ZonaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Area;
import model.Gestore;
import model.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ZonaController implements Initializable {
    @FXML
    private TableView<Zona> tablezona;
    @FXML
    private TableColumn<Zona, String> col_nomezona, col_provincia,col_view;
    @FXML
    private Button btback;

    private ObservableList<Zona> oblist;

    ZonaDaoInterface zonadata = new ZonaDao();

    private ArrayList<Zona> listzona = new ArrayList<>();
    private ArrayList<Gestore> gestl=new ArrayList<>();

    public ZonaController(){}

    public void LoadZona() throws SQLException {
        gestl=JavaFXController.datagestore;

        if(gestl.get(0).getRuolo().equals("zona"))
        {
            btback.setVisible(false);
            listzona = zonadata.LoadZonaGest(listzona,gestl);
        }else {
            listzona = zonadata.LoadZona(listzona);
        }
        setTable(listzona);
    }

    public void Backarea(ActionEvent e){
        new JavaFXController().setArea(e);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_nomezona.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        col_provincia.setCellValueFactory(new PropertyValueFactory<>("Provincia"));
        col_view.setCellValueFactory(new PropertyValueFactory<>("link"));

        oblist = FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        try {
            LoadZona();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTable(ArrayList<Zona> listzon) {

        for (int i = 0; i < listzon.size(); i++) {
            oblist.add(listzon.get(i));
            tablezona.setItems(oblist);
        }
    }
}
