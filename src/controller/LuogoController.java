package controller;

import dao.Interface.LuogoDaoInterface;
import dao.LuogoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Gestore;
import model.Luogo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LuogoController implements Initializable {
    @FXML
    private TableView<Luogo> tableluogo;
    @FXML
    private TableColumn<Luogo, String> col_nomeluogo, col_indirizzo,col_view;
    @FXML
    private Button btback;

    private ObservableList<Luogo> oblist;

    LuogoDaoInterface luoghidata = new LuogoDao();

    private ArrayList<Luogo> listluoghi = new ArrayList<>();
    private ArrayList<Gestore> gest=new ArrayList<>();
    public static String nomezona="";

    public LuogoController(){}

    public void LoadLuogo() throws SQLException {

        gest=JavaFXController.datagestore;

        if(gest.get(0).getRuolo().equals("luogo")){
            btback.setVisible(false);
            listluoghi=luoghidata.LoadLuogoGEdificio(listluoghi,gest);
        }else {
            listluoghi = luoghidata.LoadLuogo(listluoghi, nomezona);
        }
        setTable(listluoghi);
    }

    public void Backzona(ActionEvent e){
        new JavaFXController().setZona(e);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_nomeluogo.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        col_indirizzo.setCellValueFactory(new PropertyValueFactory<>("Indirizzo"));
        col_view.setCellValueFactory(new PropertyValueFactory<>("link"));

        oblist = FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        try {
            LoadLuogo();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setTable(ArrayList<Luogo> listluog) {

        for (int i = 0; i < listluog.size(); i++) {
            oblist.add(listluog.get(i));
            tableluogo.setItems(oblist);
        }
    }
}
