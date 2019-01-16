package dao.Interface;

import model.Sensore;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SensoreDaoInterface {

    int SaveMax(Sensore s) throws SQLException;
    ArrayList<Sensore> LoadSensorData()throws SQLException;
}
