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

        public GestoreDAO()
        {

        }

        @Override
        public ResultSet GestoreAuthenticationQuery(String password1) throws SQLException
        {
            //inizializzo la connessione al DB
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

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
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

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
}

