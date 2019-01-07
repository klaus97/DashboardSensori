package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GestoreDaoInterface {

    ResultSet GestoreAuthenticationQuery(String password1) throws SQLException;
}
