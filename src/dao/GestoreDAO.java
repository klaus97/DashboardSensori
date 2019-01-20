package dao;

import dao.Interface.GestoreDaoInterface;
import model.Gestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestoreDAO implements GestoreDaoInterface {

        PreparedStatement ps;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        public GestoreDAO()
        {

        }

        @Override
        public ResultSet GestoreAuthenticationQuery(String password1) throws SQLException
        {
            //preparo la query da inviare ed eseguire sul DB
            String sql = "SELECT codiceacc FROM gestore  WHERE codiceacc=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,password1);

            //ritorno il sisultato della query
            ResultSet resultSet = ps.executeQuery();

            return resultSet;
        }

        @Override
        public ArrayList<Gestore> LoadGestore(ArrayList<Gestore>gestlist,String pass) throws SQLException {

            //preparo la query da inviare ed eseguire sul DB
            String sql = "SELECT * FROM gestore g join ruolo r ON(g.ID=r.ID_gestore) WHERE codiceacc=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,pass);

            //ritorno il sisultato della query
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next())
            {
                gestlist.add(new Gestore(resultSet.getInt("ID"),resultSet.getString("g.codiceacc"),resultSet.getString("g.nome"),resultSet.getString("g.cognome"),resultSet.getString("r.tipo")));

            }
            return gestlist;
        }

    @Override
    public ArrayList<Gestore> LoadGest(ArrayList<Gestore>lg) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT * FROM gestore g join ruolo r ON(g.ID=r.ID_gestore) where g.nome LIKE ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,"%" + lg.get(0).getNome() + "%");

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        lg.clear();

        while (resultSet.next())
        {
            lg.add(new Gestore(resultSet.getInt("g.ID"),resultSet.getString("g.codiceacc"),resultSet.getString("g.nome"),resultSet.getString("g.cognome"),resultSet.getString("r.tipo")));

        }
        return lg;
    }

    @Override
    public Integer NewGestore(Gestore g) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "INSERT INTO gestore(codiceacc,nome,cognome) VALUES(?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1,g.getCodice());
        ps.setString(2,g.getNome());
        ps.setString(3,g.getCognome());

        int result = ps.executeUpdate();

        if(result==1){
            String sql2 = "INSERT INTO ruolo(tipo,ID_gestore) VALUES (?,(SELECT ID from gestore WHERE codiceacc=?))";
            ps = connection.prepareStatement(sql2);
            ps.setString(1,g.getRuolo());
            ps.setString(2,g.getCodice());

            result=ps.executeUpdate();
        }
        return result;
    }

    @Override
    public void DeleteGestore(Gestore g) throws SQLException {

        String sql = "DELETE FROM gestore WHERE codiceacc = ?";
        ps=connection.prepareStatement(sql);
        ps.setString(1,g.getCodice());
        ps.execute();
    }
}

