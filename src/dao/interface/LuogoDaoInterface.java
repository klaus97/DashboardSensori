package dao.Interface;

import model.Gestore;
import model.Luogo;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LuogoDaoInterface {
    ArrayList<Luogo> LoadLuogo(ArrayList<Luogo> luogod, String zona) throws SQLException;
    ArrayList<Luogo> LoadLuogoGEdificio(ArrayList<Luogo> luogod,ArrayList<Gestore>gest) throws SQLException;
}
