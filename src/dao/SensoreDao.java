package dao;

import dao.Interface.SensoreDaoInterface;
import model.Sensore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SensoreDao implements SensoreDaoInterface {

    //inizializzo la connessione al DB
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    PreparedStatement ps;
    ArrayList<Sensore> sensorlist = new ArrayList<>();

    @Override
    public int SaveMax(Sensore s) throws SQLException {


        String sql="UPDATE sensore SET massimale=? WHERE cod=?";
        ps=connection.prepareStatement(sql);
        ps.setInt(1,s.getMassimale());
        ps.setString(2,s.getCodices());
        return ps.executeUpdate();
    }

    public ArrayList<Sensore> LoadSensorData()throws SQLException {

        String sql="SELECT * from sensore s join dato d group by s.ID";
        ps=connection.prepareStatement(sql);

        ResultSet resultSet=ps.executeQuery();
        while(resultSet.next())
        {
            sensorlist.add(new Sensore(resultSet.getString("s.cod"),resultSet.getBoolean("s.stato"),resultSet.getInt("s.massimale"),resultSet.getInt("s.freq"),null,resultSet.getString("d.tipo"),null));
        }

        return sensorlist;

    }

    @Override
    public ArrayList<Sensore> LoadSensor(ArrayList<Sensore> ls) throws SQLException {

        String sql="SELECT * from sensore s where cod LIKE ?";
        ps=connection.prepareStatement(sql);
        ps.setString(1,"%" + ls.get(0).getCodices() + "%");

        ResultSet resultSet=ps.executeQuery();
        ls.clear();
        while(resultSet.next())
        {
            ls.add(new Sensore(resultSet.getString("s.cod"),resultSet.getBoolean("s.stato"),resultSet.getInt("s.massimale"),null,null,null,null));
        }
        return ls;

    }

    @Override
    public void DeleteSensore(Sensore s) throws SQLException {

        String sql = "DELETE FROM sensore WHERE cod = ?";
        ps=connection.prepareStatement(sql);
        ps.setString(1,s.getCodices());
        ps.execute();
    }

    @Override
    public Integer NewSensoreEdificio(Sensore s, String l, String c) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql2 = "INSERT INTO sensore(cod,massimale,ID_stanza) VALUES(?,?,(SELECT s.ID from stanza s join edificio e ON(s.ID_edificio=e.ID) Where s.nome=? AND e.nome=?))";
        ps = connection.prepareStatement(sql2);
        ps.setString(1,s.getCodices());
        ps.setInt(2,s.getMassimale());
        ps.setString(3,c);
        ps.setString(4,l);

        return ps.executeUpdate();
    }

    @Override
    public Integer NewSensoreLuogoA(Sensore s,String luogo) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "INSERT INTO sensore(cod,massimale,ID_luogoaperto) VALUES(?,?,(SELECT ID from luogoaperto l Where l.nome=?))";
        ps = connection.prepareStatement(sql);
        ps.setString(1,s.getCodices());
        ps.setInt(2,s.getMassimale());
        ps.setString(3,luogo);

        return ps.executeUpdate();
    }

}
