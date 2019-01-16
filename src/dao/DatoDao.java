package dao;

import dao.Interface.DatoDaoInterface;
import model.Sensore;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DatoDao implements DatoDaoInterface {

    PreparedStatement ps;
    ArrayList<Sensore> datalist = new ArrayList<>();

    public DatoDao(){}

    @Override
    public ArrayList<Sensore> LoadData()throws SQLException
    {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT s.cod,d.valore,d.tipo,i.datainvio,s.massimale,s.stato,s.freq FROM invia i join(SELECT max(c.ID_dato) as maxdata,c.ID_sensore from invia c group by c.ID_sensore)inv ON(inv.ID_sensore=i.ID_sensore) AND(inv.maxdata=i.ID_dato) JOIN dato d ON(d.ID=i.ID_dato) join sensore s ON(i.ID_sensore=s.ID) group by s.cod,i.datainvio";
        ps = connection.prepareStatement(sql);

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next()) {
            datalist.add(new Sensore(resultSet.getString("s.cod"),resultSet.getBoolean("s.stato"),resultSet.getInt("s.massimale"),resultSet.getInt("s.freq"), resultSet.getInt("d.valore"), resultSet.getString("d.tipo"), resultSet.getDate("i.datainvio")));
        }
        return datalist;
    }

    @Override
    public void SendData(ArrayList<Sensore> dati) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        for(Sensore s:dati) {

            String sql = "INSERT INTO dato(valore,tipo) VALUES(?,?)";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,s.getValore());
            ps.setString(2,s.getTipo());
            ps.executeUpdate();

            String sql1="UPDATE sensore SET stato=?,freq=? WHERE cod=?";
            ps=connection.prepareStatement(sql1);
            ps.setBoolean(1,s.getStato());
            ps.setInt(2,s.getFrequenza());
            ps.setString(3,s.getCodices());
            ps.executeUpdate();

            String sql2="INSERT INTO invia(ID_sensore,ID_dato,datainvio) VALUES((SELECT ID from sensore s WHERE s.cod=?),(SELECT ID from dato ORDER BY ID DESC LIMIT 1),?)";
            ps=connection.prepareStatement(sql2);
            ps.setString(1,s.getCodices());
            java.sql.Date sDate = new java.sql.Date(s.getDatainvio().getTime());
            ps.setDate(2, sDate);
            ps.executeUpdate();
        }
    }

    @Override
    public void SendDataFreq(ArrayList<Sensore> dati) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        System.out.print("\n");
        System.out.println("SONO IN SEND DATA FREQ");

        Iterator<Sensore> itr = dati.iterator();
        while(itr.hasNext()){
            Sensore temp= itr.next();
            if(temp.getFrequenza()>=3){
             datalist.add(temp);
            }
        }

        for(Sensore s:datalist) {

            String sql = "INSERT INTO dato(valore,tipo) VALUES(?,?)";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,s.getValore());
            ps.setString(2,s.getTipo());
            ps.executeUpdate();

            String sql1="UPDATE sensore SET stato=?,freq=? WHERE cod=?";
            ps=connection.prepareStatement(sql1);
            ps.setBoolean(1,s.getStato());
            ps.setInt(2,s.getFrequenza());
            ps.setString(3,s.getCodices());
            ps.executeUpdate();

            String sql2="INSERT INTO invia(ID_sensore,ID_dato,datainvio) VALUES((SELECT ID from sensore s WHERE s.cod=?),(SELECT ID from dato ORDER BY ID DESC LIMIT 1),?)";
            ps=connection.prepareStatement(sql2);
            ps.setString(1,s.getCodices());
            java.sql.Date sDate = new java.sql.Date(s.getDatainvio().getTime());
            ps.setDate(2, sDate);
            ps.executeUpdate();
        }
    }
}
