package dao;

import dao.Interface.ZonaDaoInterface;
import model.Gestore;
import model.Zona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZonaDao implements ZonaDaoInterface {

    PreparedStatement ps;

    @Override
    public ArrayList<Zona> LoadZona(ArrayList<Zona> zonal) throws SQLException {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT z.nome,a.nome from zona z join area a on(z.ID_area=a.ID)";
        ps = connection.prepareStatement(sql);

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            zonal.add(new Zona(resultSet.getString("z.nome"),resultSet.getString("a.nome")));
        }
        return zonal;
    }

    @Override
    public ArrayList<Zona> LoadZonaGest(ArrayList<Zona> zonal, ArrayList<Gestore> gest) throws SQLException {
        //inizializzo la connessione al DB
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        //preparo la query da inviare ed eseguire sul DB
        String sql = "SELECT z.nome,a.nome from zona z join area a on(z.ID_area=a.ID) join gestisce g ON(z.ID=g.ID_zona) WHERE g.ID_gestore=?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1,gest.get(0).getId());

        //ritorno il sisultato della query
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            zonal.add(new Zona(resultSet.getString("z.nome"),resultSet.getString("a.nome")));
        }
        return zonal;
    }
}
