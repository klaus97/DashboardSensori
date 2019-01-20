package dao.Interface;

import model.Dato;
import model.Luogo;
import model.Sensore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DatoDaoInterface {

     ArrayList<Sensore> LoadData() throws SQLException;
     ArrayList<Sensore> LoadDataEdificio(Luogo l) throws SQLException;
     ArrayList<Sensore> LoadDataLuogoAperto(Luogo l) throws SQLException;
     void SendData(ArrayList<Sensore> dati) throws SQLException;
     void SendDataFreq(ArrayList<Sensore> dati) throws SQLException;
}
