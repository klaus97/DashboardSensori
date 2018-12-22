package controller;

import dao.DatoDao;
import dao.DatoDaoInterface;
import model.Dato;
import model.Sensore;

import java.sql.SQLException;
import java.util.ArrayList;

public class SensoreController {

    ArrayList<Sensore> listdati = new ArrayList<>();
    public SensoreController(){}

    public ArrayList<Sensore> LoadData() throws SQLException
    {
        DatoDaoInterface datoDaoInterface = new DatoDao();

        return listdati=datoDaoInterface.LoadData();
    }
}
