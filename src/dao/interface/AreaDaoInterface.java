package dao.Interface;

import model.Area;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AreaDaoInterface {

    ArrayList<Area> LoadArea(ArrayList<Area> areal)throws SQLException;
}
