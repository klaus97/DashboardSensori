package controller;

import dao.AreaDao;
import dao.Interface.AreaDaoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Area;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AreaController implements Initializable {

    @FXML
    private TableView<Area> tablearea;
    @FXML
    private TableColumn<Area, String> col_nomearea, col_regione,col_view;

    private ObservableList<Area> oblist;

    AreaDaoInterface arealist = new AreaDao();

    private ArrayList<Area> listarea = new ArrayList<>();

    public AreaController() {}

    public void LoadArea() throws SQLException {
        listarea = arealist.LoadArea(listarea);
        setTable(listarea);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_nomearea.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        col_regione.setCellValueFactory(new PropertyValueFactory<>("Regione"));
        col_view.setCellValueFactory(new PropertyValueFactory<>("link"));

        oblist = FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        try {
            LoadArea();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setTable(ArrayList<Area> listare) {

        for (int i = 0; i < listare.size(); i++) {
            oblist.add(listare.get(i));
            tablearea.setItems(oblist);
        }
    }

}
