package controller;

import dao.AreaDao;
import dao.Interface.AreaDaoInterface;
import dao.Interface.LuogoDaoInterface;
import dao.Interface.ZonaDaoInterface;
import dao.LuogoDao;
import dao.ZonaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Area;
import model.Luogo;
import model.Zona;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestioneAree implements Initializable {

    @FXML
    private TableView<Area> tablesearch;
    @FXML
    private TableColumn<Area,Integer> col_id;
    @FXML
    private TableColumn<Area,String> col_nome,col_regione;
    @FXML
    private TextField txtsearch,txtnome,txtregione,txtindirizzo;
    @FXML
    private Label lbarea;
    @FXML
    private ChoiceBox<String> choicebox,cbAreaZona;

    private ObservableList<Area> oblist;
    ArrayList<Area> listarea = new ArrayList<>();
    AreaDaoInterface area = new AreaDao();
    ArrayList<Zona> listzona = new ArrayList<>();
    ZonaDaoInterface zonal = new ZonaDao();
    ArrayList<Luogo> listluogo = new ArrayList<>();
    LuogoDaoInterface luogol = new LuogoDao();
    Alert resp = new Alert(null);


    public void Delete() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are going to delete this area. Are you sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Area Deleter = tablesearch.getSelectionModel().getSelectedItem();
            tablesearch.getItems().removeAll(tablesearch.getSelectionModel().getSelectedItem());
            area.DeleteArea(Deleter);
        }
    }

    public void Search() throws SQLException
    {
        listarea.clear();
        tablesearch.getItems().removeAll(oblist);      //pulisco la tableview prima di inserire nuovi dati

        listarea.add(new Area(null,txtsearch.getText(),null));
        listarea=area.SearchArea(listarea);
        setTable(listarea);
    }

    public void ChangeText() throws SQLException {

        txtregione.clear();
        txtnome.clear();
        txtindirizzo.clear();

        if (choicebox.getValue().equals("Area")) {

            txtindirizzo.setVisible(false);
            txtregione.setVisible(true);
            txtregione.setPromptText("regione");
            lbarea.setVisible(false);
            cbAreaZona.setVisible(false);

        } else {
            if (choicebox.getValue().equals("Zona")) {

                txtindirizzo.setVisible(false);
                txtregione.setVisible(false);
                lbarea.setVisible(true);
                cbAreaZona.setVisible(true);
                cbAreaZona.getItems().clear();
                listarea.clear();
                listarea=area.LoadArea(listarea);

                for(Area a : listarea){
                    cbAreaZona.getItems().add(a.getNome());
                }

            } else if (choicebox.getValue().equals("Luogo Aperto") || choicebox.getValue().equals("Edificio")) {
                txtregione.setVisible(false);
                txtindirizzo.setVisible(true);
                lbarea.setVisible(true);
                cbAreaZona.setVisible(true);
                cbAreaZona.getItems().clear();
                listzona.clear();
                listzona=zonal.LoadZona(listzona);
                lbarea.setText("Presente nella Zona:");

                for(Zona z : listzona){
                    cbAreaZona.getItems().add(z.getNome());
                }

            }else {

                txtindirizzo.setVisible(false);
                txtregione.setVisible(true);
                txtregione.setPromptText("piano");
                lbarea.setVisible(true);
                cbAreaZona.setVisible(true);
                cbAreaZona.getItems().clear();
                listluogo.clear();
                listluogo=luogol.LoadEdificio(listluogo);
                lbarea.setText("In quale edificio ? :");

                for(Luogo l : listluogo){
                    cbAreaZona.getItems().add(l.getNome());
                }
            }
        }
    }

    public void Salva() throws SQLException {
        String z="";

        if(choicebox.getValue().equals("Area")){

            if(!txtnome.getText().equals("") && !txtregione.getText().equals(""))
            {
                int r=area.NewArea(new Area(null,txtnome.getText(),txtregione.getText()));

                if(r==1){
                    resp.setAlertType(Alert.AlertType.INFORMATION);
                    resp.setContentText("Salvataggio Area Riuscito!!");
                }else{
                    resp.setAlertType(Alert.AlertType.ERROR);
                    resp.setContentText("Salvataggio Area Fallito!!");
                }
            }else{
                resp.setAlertType(Alert.AlertType.ERROR);
                resp.setContentText("Non è possibile lasciare dei campi vuoti,Riprova!");
            }
        }else if(choicebox.getValue().equals("Zona")){

            if(!txtnome.getText().equals("")){

                int r=zonal.NewZona(new Zona(txtnome.getText(),cbAreaZona.getValue()));

                if(r==1){
                    resp.setAlertType(Alert.AlertType.INFORMATION);
                    resp.setContentText("Salvataggio Zona Riuscito!!");
                }else{
                    resp.setAlertType(Alert.AlertType.ERROR);
                    resp.setContentText("Salvataggio Zona Fallito!!");
                }
            }else{
                resp.setAlertType(Alert.AlertType.ERROR);
                resp.setContentText("Non è possibile lasciare dei campi vuoti,Riprova!");
            }
        }else if(choicebox.getValue().equals("Luogo Aperto")){

            if(!txtnome.getText().equals("") && !txtindirizzo.getText().equals("")){

                int r=luogol.NewLuogo(new Luogo(null,txtnome.getText(),txtindirizzo.getText(),null,null),z=cbAreaZona.getValue());

                if(r==1){
                    resp.setAlertType(Alert.AlertType.INFORMATION);
                    resp.setContentText("Salvataggio Luogo Aperto Riuscito!!");
                }else{
                    resp.setAlertType(Alert.AlertType.ERROR);
                    resp.setContentText("Salvataggio Luogo Aperto Fallito!!");
                }
            }else{
                resp.setAlertType(Alert.AlertType.ERROR);
                resp.setContentText("Non è possibile lasciare dei campi vuoti,Riprova!");
            }
        }else if(choicebox.getValue().equals("Edificio")){

            if(!txtnome.getText().equals("") && !txtindirizzo.getText().equals("")){

                int r=luogol.NewEdificio(new Luogo(null,txtnome.getText(),txtindirizzo.getText(),null,null),z=cbAreaZona.getValue());

                if(r==1){
                    resp.setAlertType(Alert.AlertType.INFORMATION);
                    resp.setContentText("Salvataggio Edificio Riuscito!!");
                }else{
                    resp.setAlertType(Alert.AlertType.ERROR);
                    resp.setContentText("Salvataggio Edificio Fallito!!");
                }
            }else{
                resp.setAlertType(Alert.AlertType.ERROR);
                resp.setContentText("Non è possibile lasciare dei campi vuoti,Riprova!");
            }
        }else{

            if(!txtnome.getText().equals("") && !txtregione.getText().equals("")){

                int r=luogol.NewStanza(new Luogo(null,cbAreaZona.getValue(),null,txtnome.getText(), Integer.parseInt(txtregione.getText())));

                if(r==1){
                    resp.setAlertType(Alert.AlertType.INFORMATION);
                    resp.setContentText("Salvataggio Stanza Riuscito!!");
                }else{
                    resp.setAlertType(Alert.AlertType.ERROR);
                    resp.setContentText("Salvataggio Stanza Fallito!!");
                }
            }else{
                resp.setAlertType(Alert.AlertType.ERROR);
                resp.setContentText("Non è possibile lasciare dei campi vuoti,Riprova!");
            }
        }

        resp.showAndWait();
    }

    public void Back(ActionEvent e){
        new JavaFXController().setAdminPanel(e);
    }

    private void setTable(ArrayList<Area> la) {

        for (int i = 0; i < la.size(); i++) {
            oblist.add(la.get(i));
            tablesearch.setItems(oblist);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_regione.setCellValueFactory(new PropertyValueFactory<>("regione"));

        oblist=FXCollections.observableArrayList(); //dichiaro l'oblist nel metodo initialize della classe

        choicebox.getItems().addAll("Area","Zona","Luogo Aperto","Edificio","Stanza");
        choicebox.setValue("Area");

    }

}
