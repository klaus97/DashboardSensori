package dao.Interface;

import model.Gestore;
import model.Zona;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ZonaDaoInterface {

    ArrayList<Zona> LoadZona(ArrayList<Zona> zonal) throws SQLException;
    ArrayList<Zona> LoadZonaGest(ArrayList<Zona> zonal, ArrayList<Gestore> gest) throws SQLException;
}
