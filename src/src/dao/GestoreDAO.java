package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestoreDAO implements dao.GestoreDaoInterface {

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
}

