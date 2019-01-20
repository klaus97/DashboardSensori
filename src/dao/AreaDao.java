package dao;

import dao.Interface.AreaDaoInterface;
import model.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaDao implements AreaDaoInterface {

    //inizializzo la connessione al DB
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    PreparedStatement ps;

    public ArrayList<Area> LoadArea(ArrayList<Area> areal) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT nome,regione from area";
        ps = connection.prepareStatement(sql);

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            areal.add(new Area(null,resultSet.getString("nome"), resultSet.getString("regione")));
        }
        return areal;
    }

    @Override
    public ArrayList<Area> SearchArea(ArrayList<Area> a) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT * FROM area where nome LIKE ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,"%" + a.get(0).getNome() + "%");

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        a.clear();

        while (resultSet.next())
        {
            a.add(new Area(resultSet.getInt("ID"),resultSet.getString("nome"),resultSet.getString("regione")));
        }
        return a;
    }

    @Override
    public void DeleteArea(Area a) throws SQLException {

        String sql = "DELETE FROM area WHERE ID = ?";
        ps=connection.prepareStatement(sql);
        ps.setInt(1,a.getId());
        ps.execute();
    }

    @Override
    public Integer NewArea(Area a) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "INSERT INTO area(nome,regione) VALUES(?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,a.getNome());
        ps.setString(2,a.getRegione());

        int result = ps.executeUpdate();

        return result;
    }
}
