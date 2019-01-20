package controller;

import dao.GestoreDAO;
import dao.Interface.GestoreDaoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Gestore;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestioneGestori implements Initializable {

    @FXML
    private TableView<Gestore> tablegest;
    @FXML
    private TableColumn<Gestore,Integer> col_id;
    @FXML
    private TableColumn<Gestore,String> col_nome,col_cognome,col_ruolo,col_cod;
    @FXML
    private TextField txtcodeacc,txtnome,txtcognome,txtruolo,txtsearch;
    private ObservableList<Gestore> oblist;
    ArrayList<Gestore> lgest = new ArrayList<>();
    GestoreDaoInterface listgest = new GestoreDAO();
    Alert resp = new Alert(null);

    public GestioneGestori(){}

    public void NewGestore() throws SQLException {

        if(!txtcodeacc.getText().equals("") && !txtcognome.getText().equals("") && !txtnome.getText().equals("") && !txtruolo.getText().equals("")){

            int r=listgest.NewGestore(new Gestore(null,txtcodeacc.getText(),txtnome.getText(),txtcognome.getText(),txtruolo.getText()));

            if(r==1){
                resp.setAlertType(Alert.AlertType.INFORMATION);
                resp.setContentText("Salvataggio Gestore Riuscito!!");
            }else{
                resp.setAlertType(Alert.AlertType.ERROR);
                resp.setContentText("Salvataggio Gestore Fallito!!");
            }

        }else{
            resp.setAlertType(Alert.AlertType.ERROR);
            resp.setContentText("Non è possibile lasciare dei campi vuoti,Riprova!");
        }
        resp.showAndWait();

        txtruolo.clear();
        txtnome.clear();
        txtcognome.clear();
        txtcodeacc.clear();
    }

    public void Delete() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are going to delete this Account. Are you sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            Gestore Deleter= tablegest.getSelectionModel().getSelectedItem();
            tablegest.getItems().removeAll(tablegest.getSelectionModel().getSelectedItem());
            listgest.DeleteGestore(Deleter);
        }
    }

    public void Search() throws SQLException
    {
        lgest.clear();
        tablegest.getItems().removeAll(oblist);      //pulisco la tableview prima di inserire nuovi dati

        lgest.add(new Gestore(null,null,txtsearch.getText(),null,null));
        lgest=listgest.LoadGest(lgest);
        setTable(lgest);
    }

    public void Back(ActionEvent e){
        new JavaFXController().setAdminPanel(e);
    }

    private void setTable(ArrayList<Gestore> lg) {

        for (int i = 0; i < lg.size(); i++) {
            oblist.add(lg.get(i));
            tablegest.setItems(oblist);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_cod.setCellValueFactory(new PropertyValueFactory<>("codice"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        col_ruolo.setCellValueFactory(new PropertyValueFactory<>("ruolo"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

    }
}
