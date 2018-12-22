package dao;

import model.Dato;
import model.Sensore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatoDao implements dao.DatoDaoInterface {

    PreparedStatement ps;

    public DatoDao(){}

    @Override
    public ArrayList<Sensore> LoadData()throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT s.cod,d.valore,d.tipo,i.datainvio,s.massimale FROM invia i join(SELECT max(c.datainvio) as maxdata,c.ID_sensore from invia c group by c.ID_sensore)inv ON(inv.ID_sensore=i.ID_sensore) AND(inv.maxdata=i.datainvio) JOIN dato d ON(d.ID=i.ID_dato) join sensore s ON(i.ID_sensore=s.ID) group by s.cod,i.datainvio";
        ps = connection.prepareStatement(sql);

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        ArrayList<Sensore> datalist = new ArrayList<>();

        while(resultSet.next()) {
            datalist.add(new Sensore(resultSet.getString("s.cod"),null,resultSet.getInt("s.massimale"), resultSet.getInt("d.valore"), resultSet.getString("d.tipo"), resultSet.getDate("i.datainvio")));
        }
        return datalist;
    }

}
