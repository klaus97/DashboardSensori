package controller;


import dao.GestoreDAO;
import dao.GestoreDaoInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import java.sql.*;

public class LoginController
{
    @FXML
    public PasswordField textpassword;
    @FXML
    private Label result;

    public LoginController()
    {
    }


    public void Login (ActionEvent event)
    {
        try {
            GestoreDaoInterface userDaoInterface = new GestoreDAO();

            ResultSet resultSet = userDaoInterface.GestoreAuthenticationQuery(textpassword.getText());

            if(resultSet.next())
            {
                //userInfoInterface.UserInfoQuery(textusername.getText());
                new JavaFXController().setdashboard(event);
            }
            else {
                result.setText("invalid code");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
