package dao.Interface;

import model.Sensore;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SensoreDaoInterface {

    int SaveMax(Sensore s) throws SQLException;
    ArrayList<Sensore> LoadSensorData()throws SQLException;
    ArrayList<Sensore> LoadSensor(ArrayList<Sensore> ls)throws SQLException;
    void DeleteSensore(Sensore s)throws SQLException;
    Integer NewSensoreEdificio(Sensore s,String l,String c)throws SQLException;
    Integer NewSensoreLuogoA(Sensore s,String l)throws SQLException;
}
