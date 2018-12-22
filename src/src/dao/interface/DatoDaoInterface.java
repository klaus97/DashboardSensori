package dao;

import model.Dato;
import model.Sensore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DatoDaoInterface {

     ArrayList<Sensore> LoadData() throws SQLException;
}
