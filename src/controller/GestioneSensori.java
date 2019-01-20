package controller;

import dao.Interface.LuogoDaoInterface;
import dao.Interface.SensoreDaoInterface;
import dao.LuogoDao;
import dao.SensoreDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Luogo;
import model.Sensore;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestioneSensori implements Initializable {

    @FXML
    private TableView<Sensore> tablesensore;
    @FXML
    private TableView<Luogo> tableluogo;
    @FXML
    private TableColumn<Sensore,String> col_cod;
    @FXML
    private TableColumn<Sensore,Boolean> col_stato;
    @FXML
    private TableColumn<Sensore,Integer> col_max;
    @FXML
    private TableColumn<Luogo,String> col_luogo,col_ind;
    @FXML
    private ChoiceBox<String> cbluogo,cbstanza,cbluogoC;
    @FXML
    private TextField txtcode,txtmax,txtsearch;
    @FXML
    private Label lbstanza,lbluogo;

    private ObservableList<Sensore> oblist;
    ArrayList<Sensore> listsens = new ArrayList<>();
    SensoreDaoInterface sensdao = new SensoreDao();
    private ObservableList<Luogo> oblist2;
    ArrayList<Luogo> listluogo = new ArrayList<>();
    LuogoDaoInterface luogodao = new LuogoDao();
    Alert resp = new Alert(null);
    Integer r =0;

    public void CreateSensore() throws SQLException {

        if(!txtcode.getText().equals("") && !txtmax.getText().equals("")){

            if(cbluogo.getValue().equals("Edificio")) {

                r = sensdao.NewSensoreEdificio(new Sensore(txtcode.getText(), null, Integer.parseInt(txtmax.getText()), null, null, null, null), cbluogoC.getValue(), cbstanza.getValue());
            }
            else{
                r = sensdao.NewSensoreLuogoA(new Sensore(txtcode.getText(), null, Integer.parseInt(txtmax.getText()), null, null, null, null), cbluogoC.getValue());
            }

            if(r==1){
                resp.setAlertType(Alert.AlertType.INFORMATION);
                resp.setContentText("Salvataggio Sensore Riuscito!!");
            }else{
                resp.setAlertType(Alert.AlertType.ERROR);
                resp.setContentText("Salvataggio Sensore Fallito!!");
            }

        }else {
            resp.setAlertType(Alert.AlertType.ERROR);
            resp.setContentText("Non è possibile lasciare dei campi vuoti,Riprova!");
        }
        resp.showAndWait();

        txtcode.clear();
        txtmax.clear();
        cbstanza.getItems().clear();
        cbstanza.setVisible(false);
        cbluogoC.getItems().clear();
        cbluogoC.setVisible(false);
        lbluogo.setVisible(false);
        lbstanza.setVisible(false);

    }

    public void Search() throws SQLException {

        tableluogo.setVisible(true);

        listluogo.clear();
        listsens.clear();
        tableluogo.getItems().removeAll(oblist2);
        tablesensore.getItems().removeAll(oblist);

        listsens.add(new Sensore(txtsearch.getText(),null,0,null,null,null,null));
        listsens=sensdao.LoadSensor(listsens);
        setTableSensore(listsens);

        listluogo=luogodao.LoadLuogoAdmin(listluogo,listsens);
        setTableLuogo(listluogo);

    }

    public void Delete() throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are going to delete this Sensor. Are you sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            Sensore Deleter= tablesensore.getSelectionModel().getSelectedItem();
            tablesensore.getItems().removeAll(tablesensore.getSelectionModel().getSelectedItem());
            sensdao.DeleteSensore(Deleter);
            tableluogo.setVisible(false);
        }
    }


    public void setTableSensore(ArrayList<Sensore> ls){

        for (int i = 0; i < ls.size(); i++) {
            oblist.add(ls.get(i));
            tablesensore.setItems(oblist);
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

    public void ChangeText() throws SQLException {
        cbluogoC.getItems().clear();
        cbstanza.getItems().clear();
        listluogo.clear();

        if(cbluogo.getValue().equals("Edificio"))
        {
            cbluogoC.setVisible(true);
            lbluogo.setVisible(true);
            lbstanza.setVisible(true);
            cbstanza.setVisible(true);

            listluogo=luogodao.LoadEdificio(listluogo);

            for(Luogo l : listluogo){
                cbluogoC.getItems().add(l.getNome());
            }

        }else{
            cbluogoC.setVisible(true);
            lbluogo.setVisible(true);
            cbstanza.setVisible(false);
            lbstanza.setVisible(false);
            listluogo.clear();

            listluogo=luogodao.LoadLuoghi(listluogo);

            for(Luogo l : listluogo){
                cbluogoC.getItems().add(l.getNome());
            }
        }

    }

    public void ChangeTextLuogo() throws SQLException {
        cbstanza.getItems().clear();

        if(cbluogo.getValue().equals("Edificio")){
            String nomeluogo = cbluogoC.getValue();

            listluogo.clear();
            listluogo=luogodao.LoadStanza(listluogo,nomeluogo);

            for (Luogo l : listluogo){
                cbstanza.getItems().add(l.getStanza());
            }
        }

    }

    public void Back(ActionEvent e){
        new JavaFXController().setAdminPanel(e);
    }

    public void setTableLuogo(ArrayList<Luogo> ll){

        for (int i = 0; i < ll.size(); i++) {
            oblist2.add(ll.get(i));
            tableluogo.setItems(oblist2);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_cod.setCellValueFactory(new PropertyValueFactory<>("codices"));
        col_stato.setCellValueFactory(new PropertyValueFactory<>("stato"));
        col_max.setCellValueFactory(new PropertyValueFactory<>("massimale"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        col_luogo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_ind.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));

        oblist2=FXCollections.observableArrayList();

        cbluogo.getItems().setAll("Edificio","Luogo Aperto");
        cbluogo.setValue("");

    }
}
