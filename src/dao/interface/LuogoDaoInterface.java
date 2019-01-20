package dao.Interface;

import model.Gestore;
import model.Luogo;
import model.Sensore;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LuogoDaoInterface {
    ArrayList<Luogo> LoadLuogo(ArrayList<Luogo> luogod, String zona) throws SQLException;
    ArrayList<Luogo> LoadLuogoGEdificio(ArrayList<Luogo> luogod,ArrayList<Gestore>gest) throws SQLException;
    ArrayList<Luogo> LoadEdificio(ArrayList<Luogo> ed) throws SQLException;
    Integer NewLuogo(Luogo l,String z) throws SQLException;
    Integer NewEdificio(Luogo l,String z) throws SQLException;
    Integer NewStanza(Luogo l) throws SQLException;
    ArrayList<Luogo> LoadLuogoAdmin(ArrayList<Luogo> ed, ArrayList<Sensore> s) throws SQLException;
    ArrayList<Luogo> LoadStanza(ArrayList<Luogo>l,String nomeed)throws SQLException;
    ArrayList<Luogo> LoadLuoghi(ArrayList<Luogo>l)throws SQLException;
}
