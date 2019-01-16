package dao;

import dao.Interface.LuogoDaoInterface;
import model.Gestore;
import model.Luogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LuogoDao implements LuogoDaoInterface {
    PreparedStatement ps;

    @Override
    public ArrayList<Luogo> LoadLuogo(ArrayList<Luogo> luogod, String zona) throws SQLException {

            //inizializzo la connessione al DB
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            //preparo la query da inviare ed eseguire sul DB
            String sql = "SELECT l.nome,l.indirizzo from luogoaperto l join zona z ON(l.ID_zona=z.ID) WHERE z.nome=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, zona);

            //ritorno il sisultato della query
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                luogod.add(new Luogo(resultSet.getString("l.nome"), resultSet.getString("l.indirizzo"), null, null));
            }

            String sql1 = "SELECT e.nome,e.indirizzo,s.nome,s.piano from edificio e join stanza s on(e.ID=s.ID_edificio) join zona z on(e.ID_zona=z.ID) WHERE z.nome=?";
            ps = connection.prepareStatement(sql1);
            ps.setString(1, zona);

            ResultSet resultSet1 = ps.executeQuery();

            while (resultSet1.next()) {
                luogod.add(new Luogo(resultSet1.getString("e.nome"), resultSet1.getString("e.indirizzo"), resultSet1.getString("s.nome"), resultSet1.getInt("s.piano")));
            }
        return luogod;
    }

    @Override
    public ArrayList<Luogo> LoadLuogoGEdificio(ArrayList<Luogo> luogod, ArrayList<Gestore> gest) throws SQLException {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT l.nome,l.indirizzo from luogoaperto l join zona z ON(l.ID_zona=z.ID) join monitoraluogo ml ON(l.ID=ml.ID_luogoaperto) WHERE ml.ID_gestore=?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, gest.get(0).getId());

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            luogod.add(new Luogo(resultSet.getString("l.nome"), resultSet.getString("l.indirizzo"), null, null));
        }

        String sql1 = "SELECT e.nome,e.indirizzo,s.nome,s.piano from edificio e join stanza s on(e.ID=s.ID_edificio) join zona z on(e.ID_zona=z.ID) join monitoraed me ON(e.ID=me.ID_edificio) WHERE me.ID_gestore=?";
        ps = connection.prepareStatement(sql1);
        ps.setInt(1, gest.get(0).getId());

        ResultSet resultSet1 = ps.executeQuery();

        while (resultSet1.next()) {
            luogod.add(new Luogo(resultSet1.getString("e.nome"), resultSet1.getString("e.indirizzo"), resultSet1.getString("s.nome"), resultSet1.getInt("s.piano")));
        }
        return luogod;
    }
}
