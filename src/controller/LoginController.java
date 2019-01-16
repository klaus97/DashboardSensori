package controller;


import dao.GestoreDAO;
import dao.Interface.GestoreDaoInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import model.Gestore;
import java.sql.*;
import java.util.ArrayList;

public class LoginController
{
    @FXML
    public PasswordField textpassword;
    @FXML
    private Label result;

    ArrayList<Gestore> gestlist= new ArrayList<>();

    public LoginController()
    {
    }


    public void Login (ActionEvent event)
    {
        try {

            GestoreDaoInterface gestoreDaoInterface = new GestoreDAO();

            ResultSet resultSet = gestoreDaoInterface.GestoreAuthenticationQuery(textpassword.getText());

            if(resultSet.next())
            {
                gestlist=gestoreDaoInterface.LoadGestore(gestlist,textpassword.getText());
                new JavaFXController().ViewStage(event,gestlist);
            }
            else {
                result.setText("invalid code");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
