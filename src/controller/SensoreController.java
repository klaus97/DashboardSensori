package controller;

import dao.DatoDao;
import dao.DatoDaoInterface;
import model.Sensore;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class SensoreController {

    static ArrayList<Sensore> listsensor = new ArrayList<>();
    DatoDaoInterface datoDaoInterface = new DatoDao();
    ArrayList<Sensore> listdati = new ArrayList<>();


    public ArrayList<Sensore> GenerateVariable()
    {

        if(listsensor == null)
        {
            try {
                listsensor=datoDaoInterface.LoadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Random random=new Random();
        boolean stato;
        int valore;

        for(int i=0; i<listsensor.size();i++)
        {
            stato=random.nextBoolean();

            Date dnow= new Date();
            SimpleDateFormat ft =
                    new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
            String data=ft.format(dnow);
            try {
                dnow=ft.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(listsensor.get(i).getTipo().equals("temperatura"))           //controllo se il sensore acquisisce un valore relativo alla temperatura
            {
                int min=-20;
                int max=40;
                int med= ((max-min) + 1);
                valore=random.nextInt(med) + min;

                System.out.println(listsensor.get(i).codices + " " + valore + " " + stato);

                listdati.add(new Sensore(listsensor.get(i).getCodices(),stato,null,valore,listsensor.get(i).getTipo(),dnow));
            }
            if (listsensor.get(i).getTipo().equals("umidità")) {            //controllo se il sensore acquisisce un valore relativo all'umidità
                int min = 0;
                int max = 100;
                int med = ((max - min) + 1);
                valore = random.nextInt(med) + min;

                System.out.println(listsensor.get(i).codices + " " + valore + " " + stato);

                listdati.add(new Sensore(listsensor.get(i).getCodices(), stato, null, valore, listsensor.get(i).getTipo(), dnow));
            }
        }
        return listdati;
    }

    public void InvioDati(){

        try {
            datoDaoInterface.SendData(listdati);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

