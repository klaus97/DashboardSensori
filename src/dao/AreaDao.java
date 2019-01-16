package dao;

import dao.Interface.AreaDaoInterface;
import model.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaDao implements AreaDaoInterface {

    PreparedStatement ps;

    public ArrayList<Area> LoadArea(ArrayList<Area> areal) throws SQLException {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT nome,regione from area";
        ps = connection.prepareStatement(sql);

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            areal.add(new Area(resultSet.getString("nome"), resultSet.getString("regione")));
        }
        return areal;
    }
}
