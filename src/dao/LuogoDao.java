package dao;

import dao.Interface.LuogoDaoInterface;
import model.Gestore;
import model.Luogo;
import model.Sensore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LuogoDao implements LuogoDaoInterface {

    PreparedStatement ps;
    //inizializzo la connessione al DB
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    @Override
    public ArrayList<Luogo> LoadLuogo(ArrayList<Luogo> luogod, String zona) throws SQLException {

            //preparo la query da inviare ed eseguire sul DB
            String sql = "SELECT l.ID,l.nome,l.indirizzo from luogoaperto l join zona z ON(l.ID_zona=z.ID) WHERE z.nome=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, zona);

            //ritorno il sisultato della query
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                luogod.add(new Luogo(resultSet.getInt("l.ID"),resultSet.getString("l.nome"), resultSet.getString("l.indirizzo"), "", null));
            }

            String sql1 = "SELECT s.ID,e.nome,e.indirizzo,s.nome,s.piano from edificio e join stanza s on(e.ID=s.ID_edificio) join zona z on(e.ID_zona=z.ID) WHERE z.nome=? group by e.ID";
            ps = connection.prepareStatement(sql1);
            ps.setString(1, zona);

            ResultSet resultSet1 = ps.executeQuery();

            while (resultSet1.next()) {
                luogod.add(new Luogo(resultSet1.getInt("s.ID"),resultSet1.getString("e.nome"), resultSet1.getString("e.indirizzo"), resultSet1.getString("s.nome"), resultSet1.getInt("s.piano")));
            }
        return luogod;
    }

    @Override
    public ArrayList<Luogo> LoadLuogoGEdificio(ArrayList<Luogo> luogod, ArrayList<Gestore> gest) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT l.ID,l.nome,l.indirizzo from luogoaperto l join zona z ON(l.ID_zona=z.ID) join monitoraluogo ml ON(l.ID=ml.ID_luogoaperto) WHERE ml.ID_gestore=?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, gest.get(0).getId());

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            luogod.add(new Luogo(resultSet.getInt("l.ID"),resultSet.getString("l.nome"), resultSet.getString("l.indirizzo"), "", null));
        }

        String sql1 = "SELECT s.ID,e.nome,e.indirizzo,s.nome,s.piano from edificio e join stanza s on(e.ID=s.ID_edificio) join zona z on(e.ID_zona=z.ID) join monitoraed me ON(e.ID=me.ID_edificio) WHERE me.ID_gestore=?";
        ps = connection.prepareStatement(sql1);
        ps.setInt(1, gest.get(0).getId());

        ResultSet resultSet1 = ps.executeQuery();

        while (resultSet1.next()) {
            luogod.add(new Luogo(resultSet1.getInt("s.ID"),resultSet1.getString("e.nome"), resultSet1.getString("e.indirizzo"), resultSet1.getString("s.nome"), resultSet1.getInt("s.piano")));
        }
        return luogod;
    }

    @Override
    public ArrayList<Luogo> LoadEdificio(ArrayList<Luogo> ed) throws SQLException {

        String sql = "SELECT nome from edificio";
        ps = connection.prepareStatement(sql);

        ResultSet resultSet1 = ps.executeQuery();

        while (resultSet1.next()) {
            ed.add(new Luogo(null,resultSet1.getString("nome"), null , null, null));
        }
        return ed;
    }

    @Override
    public Integer NewLuogo(Luogo l,String zona) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "INSERT INTO luogoaperto(nome,indirizzo,ID_zona) VALUES(?,?,(SELECT ID from zona z Where z.nome=?))";
        ps = connection.prepareStatement(sql);
        ps.setString(1,l.getNome());
        ps.setString(2,l.getIndirizzo());
        ps.setString(3,zona);

        int result = ps.executeUpdate();

        return result;
    }

    @Override
    public Integer NewEdificio(Luogo l,String zona) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "INSERT INTO edificio(nome,indirizzo,ID_zona) VALUES(?,?,(SELECT ID from zona z Where z.nome=?))";
        ps = connection.prepareStatement(sql);
        ps.setString(1,l.getNome());
        ps.setString(2,l.getIndirizzo());
        ps.setString(3,zona);

        int result = ps.executeUpdate();

        return result;
    }

    @Override
    public Integer NewStanza(Luogo l) throws SQLException {

        //preparo la query da inviare ed eseguire sul DB
        String sql = "INSERT INTO stanza(nome,piano,ID_edificio) VALUES(?,?,(SELECT ID from edificio e Where e.nome=?))";
        ps = connection.prepareStatement(sql);
        ps.setString(1,l.getStanza());
        ps.setInt(2,l.getPiano());
        ps.setString(3,l.getNome());

        int result = ps.executeUpdate();

        return result;
    }

    @Override
    public ArrayList<Luogo> LoadLuogoAdmin(ArrayList<Luogo> ed, ArrayList<Sensore> ls) throws SQLException {

        for(Sensore s:ls) {
            //preparo la query da inviare ed eseguire sul DB
            String sql = "SELECT l.nome,l.indirizzo from luogoaperto l join sensore s ON(l.ID=s.ID_luogoaperto) WHERE s.cod LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + s.getCodices() + "%");


            //ritorno il sisultato della query
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ed.add(new Luogo(null, resultSet.getString("l.nome"), resultSet.getString("l.indirizzo"), null, null));
            }

            String sql1 = "SELECT e.nome,e.indirizzo from edificio e join stanza st on(e.ID=st.ID_edificio) join sensore s on(st.ID=s.ID_stanza) WHERE s.cod LIKE ?";
            ps = connection.prepareStatement(sql1);
            ps.setString(1,"%" + s.getCodices() + "%");

            ResultSet resultSet1 = ps.executeQuery();

            while (resultSet1.next()) {
                ed.add(new Luogo(null, resultSet1.getString("e.nome"), resultSet1.getString("e.indirizzo"), null, null));
            }

        }
        return ed;
    }

    @Override
    public ArrayList<Luogo> LoadStanza(ArrayList<Luogo> l,String nomeed) throws SQLException {

        String sql = "SELECT s.nome from edificio e join stanza s ON(e.ID=s.ID_edificio) where e.nome=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1,nomeed);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            l.add(new Luogo(null,null, null , resultSet.getString("s.nome"), null));
        }
        return l;
    }

    @Override
    public ArrayList<Luogo> LoadLuoghi(ArrayList<Luogo> l) throws SQLException {

        String sql = "SELECT * from luogoaperto";
        ps = connection.prepareStatement(sql);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            l.add(new Luogo(null,resultSet.getString("nome"), null , "", null));
        }
        return l;
    }
}
