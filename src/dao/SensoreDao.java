package dao;

import dao.Interface.SensoreDaoInterface;
import model.Sensore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SensoreDao implements SensoreDaoInterface {

    PreparedStatement ps;
    ArrayList<Sensore> sensorlist = new ArrayList<>();

    @Override
    public int SaveMax(Sensore s) throws SQLException {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql="UPDATE sensore SET massimale=? WHERE cod=?";
        ps=connection.prepareStatement(sql);
        ps.setInt(1,s.getMassimale());
        ps.setString(2,s.getCodices());
        return ps.executeUpdate();
    }

    public ArrayList<Sensore> LoadSensorData()throws SQLException {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql="SELECT s.cod,d.tipo,s.freq from sensore s join invia i ON(s.ID=i.ID_sensore) join dato d ON(i.ID_dato=d.ID) group by s.ID";
        ps=connection.prepareStatement(sql);

        ResultSet resultSet=ps.executeQuery();
        while(resultSet.next())
        {
            sensorlist.add(new Sensore(resultSet.getString("s.cod"),null,null,resultSet.getInt("s.freq"),null,resultSet.getString("d.tipo"),null));
        }

        return sensorlist;

    }
}
