package dao.Interface;

import model.Area;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AreaDaoInterface {

    ArrayList<Area> LoadArea(ArrayList<Area> areal)throws SQLException;
    ArrayList<Area> SearchArea(ArrayList<Area> a)throws SQLException;
    void DeleteArea(Area a) throws SQLException;
    Integer NewArea(Area a) throws SQLException;
}
