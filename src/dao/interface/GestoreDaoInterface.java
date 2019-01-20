package dao.Interface;

import model.Gestore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GestoreDaoInterface {

    ResultSet GestoreAuthenticationQuery(String password1) throws SQLException;
    ArrayList<Gestore> LoadGestore(ArrayList<Gestore>gestlist,String pass) throws SQLException;
    ArrayList<Gestore> LoadGest(ArrayList<Gestore>lg) throws SQLException;
    Integer NewGestore(Gestore g)throws SQLException;
    void DeleteGestore(Gestore g)throws SQLException;
}
